package com.prilepskiy.presentation.detailScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.prilepskiy.common.Black
import com.prilepskiy.common.Blue600
import com.prilepskiy.common.BodyTextStyles
import com.prilepskiy.common.DEFAULT_INT
import com.prilepskiy.common.EMPTY_STRING
import com.prilepskiy.common.Gray100
import com.prilepskiy.common.Gray200
import com.prilepskiy.common.Gray80
import com.prilepskiy.common.Gray90
import com.prilepskiy.common.ID_ALL_CATEGORY
import com.prilepskiy.common.Sizes
import com.prilepskiy.common.Spaces
import com.prilepskiy.common.TAB_DETAIL
import com.prilepskiy.common.TAB_NOTE
import com.prilepskiy.common.TAB_STAGE
import com.prilepskiy.common.TitleTextStyles
import com.prilepskiy.domain.model.CategoryModel
import com.prilepskiy.domain.model.NoteModel
import com.prilepskiy.domain.model.PointModel
import com.prilepskiy.domain.model.StageModel
import com.prilepskiy.presentation.R
import com.prilepskiy.presentation.uiComponent.PhotoCardComponent
import com.prilepskiy.presentation.uiComponent.TabsStandardComponents
import com.prilepskiy.presentation.uiComponent.ToolbarStandardComponent

@Composable
fun DetailScreen(
    point: Long?,
    onPopBack: () -> Unit,
    onUpdatePoint: (Long?) -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val state = viewModel.viewState

    LaunchedEffect(point) {
        point?.let {
            viewModel.onIntent(DetailIntent.Init(it))
        }
    }

    state.point?.let { pt ->
        DetailScreen(
            point = pt,
            category = state.categoryList.find {
                it.categoryId == pt.categoryId
            } ?: CategoryModel(
                categoryId = ID_ALL_CATEGORY,
                categoryName = stringResource(R.string.all)
            ),
            notes = state.noteList,
            stages = state.stageList,
            onPopBack = onPopBack,
            onUpdatePoint = onUpdatePoint,
            onSuccessPoint = { viewModel.onIntent(DetailIntent.OnClickSuccess { onPopBack.invoke() }) },
            onDeletePoint = { viewModel.onIntent(DetailIntent.OnClickDelete { onPopBack.invoke() }) },
            addNote = {},
            addStage = {},
            onSuccessStage = {},
            onDeleteStage = {},
            onSuccessNote = {},
            onDeleteNote = {},
        )
    }

}

@Composable
fun DetailScreen(
    point: PointModel,
    category: CategoryModel,
    notes: List<NoteModel>,
    stages: List<StageModel>,
    onPopBack: () -> Unit,
    onUpdatePoint: (Long?) -> Unit,
    onSuccessPoint: () -> Unit,
    onDeletePoint: () -> Unit,
    addNote: () -> Unit,
    addStage: () -> Unit,
    onSuccessStage: () -> Unit,
    onDeleteStage: () -> Unit,
    onSuccessNote: () -> Unit,
    onDeleteNote: () -> Unit
) {
    var tabId by remember { mutableIntStateOf(DEFAULT_INT) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Blue600)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Blue600),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ToolbarStandardComponent(
                modifier = Modifier.padding(vertical = Spaces.space10),
                title = EMPTY_STRING,
                onBackPressed = onPopBack,
                textColor = Gray80,
                iconColor = Gray80,
                firstIcon = Icons.Default.ArrowBack,
                secondIcon = Icons.Default.Check,
                onSecondClick = onSuccessPoint,
                thirdIcon = Icons.Filled.Delete,
                onThirdClick = onDeletePoint
            )
            TabsStandardComponents(
                tabs = listOf(
                    "Описание",
                    "Этапы",
                    "Заметки"
                ), onClickTab = {
                    tabId = it
                }
            )

            when (tabId) {
                TAB_DETAIL -> {
                    DetailTabScreen(
                        point = point,
                        onUpdatePoint = onUpdatePoint,
                        category = category
                    )
                }

                TAB_STAGE -> {
                    StageTabScreen(
                        stages = stages,
                        onSuccessStage = onSuccessStage,
                        onDeleteStage = onDeleteStage
                    )
                }

                TAB_NOTE -> {
                    NoteTabScreen(
                        notes = notes,
                        onSuccessNote = onSuccessNote,
                        onDeleteNote = onDeleteNote
                    )
                }

                else -> {
                    DetailTabScreen(
                        point = point,
                        onUpdatePoint = onUpdatePoint,
                        category = category
                    )
                }
            }

        }
        if (tabId == TAB_STAGE || tabId == TAB_NOTE)
            FloatingActionButton(
                containerColor = Gray80,
                onClick = {
                    when (tabId) {
                        TAB_STAGE -> addStage.invoke()
                        TAB_NOTE -> addNote.invoke()
                    }

                },
                modifier = Modifier
                    .padding(vertical = Spaces.space60, horizontal = Spaces.space16)
                    .align(alignment = Alignment.BottomEnd)
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Добавить")
            }
    }
}

@Composable
fun DetailTabScreen(
    point: PointModel,
    category: CategoryModel,
    onUpdatePoint: (Long?) -> Unit,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.verticalScroll(scrollState),

        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        if (point.uri.isNotEmpty()) {
            PhotoCardComponent(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                path = point.uri
            ) {}
        } else {
            Box(
                modifier = Modifier
                    .size(Spaces.space150)
                    .background(Gray100)
            )
        }
        Text(
            modifier = Modifier
                .padding(Spaces.space8)
                .align(alignment = Alignment.Start),
            text = "Наименование:",
            style = TitleTextStyles.H4W700,
            color = Gray90
        )
        Text(
            modifier = Modifier
                .padding(Spaces.space8)
                .align(alignment = Alignment.Start),
            text = point.pointName,
            style = BodyTextStyles.Large,
            color = Gray200
        )

        Text(
            modifier = Modifier
                .padding(Spaces.space8)
                .align(alignment = Alignment.Start),
            text = "Мотивация:",
            style = TitleTextStyles.H4W700,
            color = Gray90
        )
        Text(
            modifier = Modifier
                .padding(Spaces.space8)
                .align(alignment = Alignment.Start),
            text = point.motivation,
            style = BodyTextStyles.Large,
            color = Gray200
        )

        Text(
            modifier = Modifier
                .padding(Spaces.space8)
                .align(alignment = Alignment.Start),
            text = "Награда:",
            style = TitleTextStyles.H4W700,
            color = Gray90
        )
        Text(
            modifier = Modifier
                .padding(Spaces.space8)
                .align(alignment = Alignment.Start),
            text = point.reward,
            style = BodyTextStyles.Large,
            color = Gray200
        )
        Text(
            modifier = Modifier
                .padding(Spaces.space8)
                .align(alignment = Alignment.Start),
            text = "Категория:",
            style = TitleTextStyles.H4W700,
            color = Gray90
        )
        Text(
            modifier = Modifier
                .padding(Spaces.space8)
                .align(alignment = Alignment.Start),
            text = category.categoryName,
            style = BodyTextStyles.Large,
            color = Gray200
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spaces.space16),
            onClick = { onUpdatePoint.invoke(point.pointId) },
            colors = ButtonDefaults.buttonColors(containerColor = Gray90, contentColor = Black),
            contentPadding = PaddingValues(horizontal = Spaces.space16, vertical = Spaces.space9)
        ) {
            Text(
                text = stringResource(R.string.update_point),
                color = Black,
                fontSize = Sizes.size18
            )
        }
        Box(modifier = Modifier.size(Spaces.space30))
    }
}

@Composable
fun NoteTabScreen(
    notes: List<NoteModel>,
    onSuccessNote: () -> Unit,
    onDeleteNote: () -> Unit
) {

    if (notes.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(stringResource(R.string.empty_note), color = Gray90)
        }
    } else {
        LazyColumn { }
    }

}

@Composable
fun StageTabScreen(
    stages: List<StageModel>,
    onSuccessStage: () -> Unit,
    onDeleteStage: () -> Unit
) {
    if (stages.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(stringResource(R.string.empty_stages), color = Gray90)
        }
    } else {
        LazyColumn { }
    }
}

