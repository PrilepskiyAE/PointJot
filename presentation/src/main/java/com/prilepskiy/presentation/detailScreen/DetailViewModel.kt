package com.prilepskiy.presentation.detailScreen

import androidx.lifecycle.viewModelScope
import com.prilepskiy.common.MviBaseViewModel
import com.prilepskiy.common.Reducer
import com.prilepskiy.common.deleteImageFromPrivateStorage
import com.prilepskiy.common.subscribe
import com.prilepskiy.domain.categoryUseCase.GetAllCategoryUseCase
import com.prilepskiy.domain.model.NoteModel
import com.prilepskiy.domain.model.StageModel
import com.prilepskiy.domain.noteUseCase.AddNoteUseCase
import com.prilepskiy.domain.noteUseCase.DeleteNoteUseCase
import com.prilepskiy.domain.noteUseCase.GetNoteFromPointUseCase
import com.prilepskiy.domain.pointUseCase.AddPointUseCase
import com.prilepskiy.domain.pointUseCase.DeletePointUseCase
import com.prilepskiy.domain.pointUseCase.GetPointUseCase
import com.prilepskiy.domain.stageUseCase.AddStageUseCase
import com.prilepskiy.domain.stageUseCase.DeleteStageUseCase
import com.prilepskiy.domain.stageUseCase.GetStageFromPointUseCase
import com.prilepskiy.presentation.detailScreen.DetailAction.GetCategory
import com.prilepskiy.presentation.detailScreen.DetailAction.Init
import com.prilepskiy.presentation.detailScreen.DetailAction.OnError
import com.prilepskiy.presentation.detailScreen.DetailAction.OnLoading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    detailReducer: DetailReducer,
    private val getPoint: GetPointUseCase,
    private val getAllCategoryUseCase: GetAllCategoryUseCase,
    private val getStageFromPointUseCase: GetStageFromPointUseCase,
    private val getNoteFromPointUseCase: GetNoteFromPointUseCase,
    private val deletePointUseCase: DeletePointUseCase,
    private val addPointUseCase: AddPointUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val addStageUseCase: AddStageUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val deleteStageUseCase: DeleteStageUseCase,
) : MviBaseViewModel<DetailState, DetailAction, DetailIntent>() {
    override var reducer: Reducer<DetailAction, DetailState> = detailReducer


    override fun initState(): DetailState = DetailState()
    override fun handleIntent(intent: DetailIntent) {
        when (intent) {
            is DetailIntent.Init -> {

                getAllCategoryUseCase.invoke().subscribe(viewModelScope, success = {
                    onAction(GetCategory(it))
                })

                getPoint.invoke(intent.pointId).subscribe(
                    viewModelScope, onStart = {
                        onAction(OnLoading(true))
                    }, success = { result ->
                        result.firstOrNull()?.let { point ->
                            onAction(Init(point))
                            getStageForPointAction(pointID = point.pointId) {
                                onAction(DetailAction.GetStage(it))
                            }
                            getNoteForPointAction(pointID = point.pointId) {
                                onAction(DetailAction.GetNote(it))
                            }
                        }
                    }, error = { OnError(it.localizedMessage) }
                )
            }

            is DetailIntent.OnError -> onAction(OnError(intent.error))
            is DetailIntent.OnLoading -> onAction(OnLoading(intent.isLoading))
            is DetailIntent.OnClickSuccess -> {
                viewModelScope.launch {
                    val isActive = viewState.point?.isActive ?: true
                    val point = viewState.point?.copy(isActive = !isActive)
                    point?.let {
                        addPointUseCase.invoke(it)
                        intent.onSuccess.invoke()
                    }
                }
            }

            is DetailIntent.OnClickDelete -> {
                viewModelScope.launch {
                    viewState.point?.let {
                        deletePointUseCase.invoke(it.pointId)
                        deleteImageFromPrivateStorage(it.uri)
                        intent.onDelete.invoke()
                    }
                }
            }

            is DetailIntent.AddNote -> {
                viewModelScope.launch {
                    addNoteUseCase.invoke(intent.noteModel)
                    viewState.point?.let { point ->
                        getNoteForPointAction(point.pointId) {
                            onAction(DetailAction.GetNote(it))
                        }
                    }
                }
            }

            is DetailIntent.AddStage -> {
                viewModelScope.launch {
                    addStageUseCase.invoke(intent.stageModel)
                    viewState.point?.let { point ->
                        getStageForPointAction(point.pointId) {
                            onAction(DetailAction.GetStage(it))
                        }
                    }
                }
            }

            is DetailIntent.OnDeleteNote -> {
                viewModelScope.launch {
                    deleteImageFromPrivateStorage(intent.noteModel.uri)
                    deleteNoteUseCase.invoke(intent.noteModel.noteId)
                    viewState.point?.let { point ->
                        getNoteForPointAction(point.pointId) {
                            onAction(DetailAction.GetNote(it))
                        }
                    }
                }
            }

            is DetailIntent.OnDeleteStage -> {
                viewModelScope.launch {
                    deleteStageUseCase.invoke(intent.stageModel.stageId)
                    viewState.point?.let { point ->
                        getStageForPointAction(point.pointId) {
                            onAction(DetailAction.GetStage(it))
                        }
                    }
                }
            }

            is DetailIntent.OnSuccessStage -> {
                viewModelScope.launch {
                    addStageUseCase.invoke(intent.stageModel)
                    viewState.point?.let { point ->
                        getStageForPointAction(point.pointId) {
                            onAction(DetailAction.GetStage(it))
                        }
                    }
                }

            }
        }
    }

    fun getStageForPointAction(pointID: Long, action: suspend (List<StageModel>) -> Unit) {
        getStageFromPointUseCase.invoke(pointID).subscribe(scope = viewModelScope, onStart = {
            onAction(OnLoading(true))
        }, success = { list ->
            action.invoke(list)
        }, error = {
            onAction(OnError(it.localizedMessage))
        })
    }

    fun getNoteForPointAction(pointID: Long, action: suspend (List<NoteModel>) -> Unit) {
        getNoteFromPointUseCase.invoke(pointID).subscribe(scope = viewModelScope, onStart = {
            onAction(OnLoading(true))
        }, success = { list ->
            action.invoke(list)
        }, error = {
            onAction(OnError(it.localizedMessage))
        })
    }
}
