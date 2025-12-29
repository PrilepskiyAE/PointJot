package com.prilepskiy.presentation.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.prilepskiy.common.Blue100
import com.prilepskiy.common.Blue500
import com.prilepskiy.common.Blue600
import com.prilepskiy.common.EMPTY_STRING
import com.prilepskiy.common.Gray600
import com.prilepskiy.common.Gray80
import com.prilepskiy.common.Gray90
import com.prilepskiy.common.ID_ALL_CATEGORY
import com.prilepskiy.common.Spaces
import com.prilepskiy.common.White
import com.prilepskiy.domain.model.CategoryModel
import com.prilepskiy.domain.model.PointModel
import com.prilepskiy.presentation.R
import com.prilepskiy.presentation.uiComponent.ChipsStandardTextComponent
import com.prilepskiy.presentation.uiComponent.DeleteCategoryDialogComponent
import com.prilepskiy.presentation.uiComponent.ErrorMessageComponent
import com.prilepskiy.presentation.uiComponent.InputDialogComponent
import com.prilepskiy.presentation.uiComponent.LoadingComponent
import com.prilepskiy.presentation.uiComponent.TabsStandardComponents
import com.prilepskiy.presentation.uiComponent.ToolbarStandardComponent

@Composable
fun MainScreen(goToPoint: (Int) -> Unit,goToAddPoint: (Int) -> Unit, viewModel: MainViewModel = hiltViewModel()) {
    val state = viewModel.viewState

    LaunchedEffect(Unit) {
        viewModel.onIntent(MainIntent.InitPoint)
    }

    if (state.isLoading) {
        LoadingComponent()
    } else if (!state.error.isNullOrEmpty()) {
        ErrorMessageComponent(textError = state.error) {
        }
    } else {
        MainScreen(
            categoryList = state.categoryList,
            pointList = state.pointList,
            goToPoint = goToPoint,
            onClickChip = { viewModel.onIntent(MainIntent.OnClickCategory(it)) },
            onClickLongChip = { viewModel.onIntent(MainIntent.DeleteCategory(it)) },
            onClickTabLayout = {},
            onClickAdd = { viewModel.onIntent(MainIntent.AddCategory(CategoryModel(categoryName = it))) },
            goToAddPoint=goToAddPoint
        )
    }
}

@Composable
private fun MainScreen(
    categoryList: List<CategoryModel>,
    pointList: List<PointModel>,
    onClickChip: (CategoryModel) -> Unit,
    onClickLongChip: (CategoryModel) -> Unit,
    onClickAdd: (String) -> Unit,
    onClickTabLayout: (id: Int) -> Unit,
    goToPoint: (Int) -> Unit,
    goToAddPoint: (Int) -> Unit,
) {
    var showDialogAddCategory by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var deleteCategory: CategoryModel? by remember { mutableStateOf(null) }
    if (showDialogAddCategory) {
        InputDialogComponent(
            onDismiss = { showDialogAddCategory = false },
            onConfirm = { enteredText ->
                onClickAdd.invoke(enteredText)
                showDialogAddCategory = false
            },
            title = stringResource(R.string.dialog_category_title),
            confirmText = stringResource(R.string.save),
            placeholder = stringResource(R.string.dialog_category_placeholder)
        )
    }
    if (showDeleteDialog) {
        DeleteCategoryDialogComponent(
            onDismiss = { showDeleteDialog = false },
            onConfirm = {
                deleteCategory?.let {
                    onClickLongChip.invoke(it)
                }

                showDeleteDialog = false
            },
            categoryName = deleteCategory?.categoryName ?: EMPTY_STRING
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Blue600)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Spaces.space16)
                .align(alignment = Alignment.TopCenter)
        ) {
            ToolbarStandardComponent(
                modifier = Modifier,
                title = stringResource(R.string.main_screen_title), textColor = Gray80
            )
            ChipsStandardTextComponent(
                modifier = Modifier.padding(Spaces.space16),
                list = categoryList,
                colorBackgroundActive = Blue100,
                colorBackgroundPassive = Gray90,
                colorTextActive = Blue500,
                colorTextPassive = Gray600,
                onClickLongChip = {
                    if (it.categoryId != ID_ALL_CATEGORY) {
                        deleteCategory = it
                        showDeleteDialog = true
                    }
                },
                onClickChip = onClickChip,
                onClickAdd = {
                    showDialogAddCategory = true
                }
            )
            if (pointList.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = stringResource(R.string.empty_point),
                        color = White
                    )
                }

            } else {
                LazyColumn {
                    item {
                        TabsStandardComponents(
                            tabs = listOf(
                                stringResource(R.string.main_screen_active),
                                stringResource(R.string.main_screen_closed)
                            ), onClickTab = onClickTabLayout
                        )
                    }
                    if (pointList.isEmpty()) {
                        item {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Text(
                                    modifier = Modifier.align(Alignment.Center),
                                    text = stringResource(R.string.empty_point),
                                    color = White
                                )
                            }
                        }
                    } else {
                        items(pointList.size) { index ->

                        }
                    }


                }
            }

        }
        FloatingActionButton(
            containerColor = Gray80,
            onClick = { goToAddPoint.invoke(0) },
            modifier = Modifier
                .padding(vertical = Spaces.space60, horizontal = Spaces.space16)
                .align(alignment = Alignment.BottomEnd)
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Добавить")
        }
    }

}
