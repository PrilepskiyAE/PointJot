package com.prilepskiy.presentation.mainScreen

import androidx.lifecycle.viewModelScope
import com.prilepskiy.common.DEFAULT_INT
import com.prilepskiy.common.ID_ALL_CATEGORY
import com.prilepskiy.common.ID_SECOND_CATEGORY
import com.prilepskiy.common.MviBaseViewModel
import com.prilepskiy.common.Reducer
import com.prilepskiy.common.subscribe
import com.prilepskiy.domain.categoryUseCase.AddCategoryUseCase
import com.prilepskiy.domain.categoryUseCase.DeleteCategoryUseCase
import com.prilepskiy.domain.categoryUseCase.GetAllCategoryUseCase
import com.prilepskiy.domain.model.CategoryModel
import com.prilepskiy.domain.pointUseCase.GetAllPointUseCase
import com.prilepskiy.domain.pointUseCase.GetCategoryUseCase
import com.prilepskiy.presentation.mainScreen.MainAction.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    mainReducer: MainReducer,
    private val getAllCategoryUseCase: GetAllCategoryUseCase,
    private val deleteCategoryUseCase: DeleteCategoryUseCase,
    private val addCategoryUseCase: AddCategoryUseCase,
    private val getAllPointUseCase: GetAllPointUseCase,
    private val getCategory: GetCategoryUseCase,
) : MviBaseViewModel<MainState, MainAction, MainIntent>() {
    override var reducer: Reducer<MainAction, MainState> = mainReducer


    override fun initState(): MainState = MainState()

    override fun handleIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.OnError -> onAction(OnError(intent.error))
            is MainIntent.OnLoading -> onAction(OnLoading(intent.isLoading))
            is MainIntent.OnClickPoint -> {}
            is MainIntent.AddCategory -> {
                getAllCategoryAction { list ->
                    if (list.find { it.categoryName == intent.item.categoryName } == null) {
                        addCategoryUseCase.invoke(intent.item)
                        onAction(AddCategory(intent.item))
                    }
                }
            }
            is MainIntent.DeleteCategory -> {
                viewModelScope.launch {
                    if (intent.item != viewState.categoryList.firstOrNull() && !intent.item.isActive) {
                        getAllCategoryAction { list ->
                            val result = list.find { it.categoryName == intent.item.categoryName }
                            if (result != null) {
                                deleteCategoryUseCase.invoke(result.categoryId)
                                onAction(DeleteCategory(intent.item))
                            }
                        }
                    }
                }
            }
            is MainIntent.OnClickCategory -> {
                onAction(OnClickCategory(intent.item))
                if (intent.item.categoryId == ID_ALL_CATEGORY) {
                    getAllPoint(false)
                } else {
                    getPointByCategory(intent.item.categoryId)
                }
            }
            is MainIntent.InitPoint -> {
                getAllCategoryAction { list ->
                    if (list.isEmpty()) {
                        val first = CategoryModel(
                            categoryId = ID_ALL_CATEGORY,
                            intent.first,
                            isActive = true
                        )
                        val second = CategoryModel(
                            categoryId = ID_SECOND_CATEGORY,
                            intent.second,
                            isActive = false
                        )
                        addCategoryUseCase.invoke(first)
                        addCategoryUseCase.invoke(second)
                        onAction(GetCategory(listOf(first, second)))
                    } else {
                        onAction(GetCategory(list))
                    }
                    getAllPoint(true)
                }
            }
            is MainIntent.OnClickTab -> {
                onAction(OnClickTab(intent.id == DEFAULT_INT))
                if (viewState.activeCategoryId == ID_ALL_CATEGORY) {
                    getAllPoint(false)
                } else {
                    getPointByCategory(viewState.activeCategoryId)
                }

            }
        }
    }

    fun getPointByCategory(categoryId: Long) {
        getCategory.invoke(categoryId)
            .subscribe(scope = viewModelScope, success = { list ->
                onAction(GetPoint(list.filter { viewState.isActive == it.isActive }))
            }, error = { onAction(OnError("Ой что-то пошло не так")) })
    }

    fun getAllPoint(showLoading: Boolean) {
        getAllPointUseCase.invoke().subscribe(
            scope = viewModelScope,
            onStart = {
                onAction(MainAction.OnLoading(showLoading))
            },
            success = { pointList ->
                onAction(MainAction.GetPoint(pointList.filter { viewState.isActive == it.isActive }))
            },
            error = { onAction(MainAction.OnError("Ой что-то пошло не так")) })
    }

    fun getAllCategoryAction(action: suspend (List<CategoryModel>) -> Unit) {
        getAllCategoryUseCase.invoke().subscribe(scope = viewModelScope, onStart = {
            onAction(MainAction.OnLoading(true))
        }, success = { list ->
            action.invoke(list)
        }, error = {
            onAction(MainAction.OnError("Ой что-то пошло не так"))
        })
    }
}