package com.prilepskiy.presentation.mainScreen

import androidx.lifecycle.viewModelScope
import com.prilepskiy.common.ID_ALL_CATEGORY
import com.prilepskiy.common.MviBaseViewModel
import com.prilepskiy.common.Reducer
import com.prilepskiy.common.subscribe
import com.prilepskiy.domain.categoryUseCase.AddCategoryUseCase
import com.prilepskiy.domain.categoryUseCase.DeleteCategoryUseCase
import com.prilepskiy.domain.categoryUseCase.GetAllCategoryUseCase
import com.prilepskiy.domain.model.CategoryModel
import com.prilepskiy.presentation.mainScreen.MainAction.AddCategory
import com.prilepskiy.presentation.mainScreen.MainAction.DeleteCategory
import com.prilepskiy.presentation.mainScreen.MainAction.OnClickCategory
import com.prilepskiy.presentation.mainScreen.MainAction.OnError
import com.prilepskiy.presentation.mainScreen.MainAction.OnLoading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainReducer: MainReducer,
    private val getAllCategoryUseCase: GetAllCategoryUseCase,
    private val deleteCategoryUseCase: DeleteCategoryUseCase,
    private val addCategoryUseCase: AddCategoryUseCase
) : MviBaseViewModel<MainState, MainAction, MainIntent>() {
    override var reducer: Reducer<MainAction, MainState> = mainReducer


    override fun initState(): MainState = MainState()

    init {
        getAllCategoryAction { list ->
            if (list.isEmpty()) {
                val first = CategoryModel(categoryId = ID_ALL_CATEGORY, "Все", isActive = true)
                addCategoryUseCase.invoke(first)
                onAction(MainAction.GetCategory(listOf(first)))
            } else {
                onAction(MainAction.GetCategory(list))
            }
        }
    }

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

            is MainIntent.OnClickCategory -> onAction(OnClickCategory(intent.item))
        }
    }

    fun getAllCategoryAction(action: suspend (List<CategoryModel>) -> Unit) {
        getAllCategoryUseCase.invoke().subscribe(scope = viewModelScope, onStart = {
            onAction(MainAction.OnLoading(true))
        }, success = { list ->
            onAction(MainAction.OnLoading(false))
            action.invoke(list)
        }, error = {
            onAction(MainAction.OnError("Ой что-то пошло не так"))
        })
    }
}