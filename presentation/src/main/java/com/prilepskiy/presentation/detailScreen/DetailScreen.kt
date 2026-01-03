package com.prilepskiy.presentation.detailScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.prilepskiy.common.DEFAULT_INT
import com.prilepskiy.common.EMPTY_STRING
import com.prilepskiy.common.Gray100
import com.prilepskiy.common.Gray80
import com.prilepskiy.common.Gray90
import com.prilepskiy.common.Sizes
import com.prilepskiy.common.Spaces
import com.prilepskiy.common.TAB_DETAIL
import com.prilepskiy.common.TAB_NOTE
import com.prilepskiy.common.TAB_STAGE
import com.prilepskiy.presentation.R
import com.prilepskiy.presentation.mainScreen.MainViewModel
import com.prilepskiy.presentation.uiComponent.PhotoCardComponent
import com.prilepskiy.presentation.uiComponent.TabsStandardComponents
import com.prilepskiy.presentation.uiComponent.ToolbarStandardComponent

@Composable
fun DetailScreen(point: Long?, onPopBack: () -> Unit, onUpdatePoint: (Long?) -> Unit,viewModel: DetailViewModel = hiltViewModel()) {
    DetailScreen(
        pointId = point,
        selectedImageUri = null,
        onPopBack = onPopBack,
        onUpdatePoint = onUpdatePoint,
        onSuccessPoint = {},
        onDeletePoint = {},
        addNote = {},
        addStage = {}
    )
}

@Composable
fun DetailScreen(
    pointId: Long?,
    selectedImageUri: String?,
    onPopBack: () -> Unit,
    onUpdatePoint: (Long?) -> Unit,
    onSuccessPoint: () -> Unit,
    onDeletePoint: () -> Unit,
    addNote: () -> Unit,
    addStage: () -> Unit,
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
                        pointId = pointId,
                        selectedImageUri = selectedImageUri,
                        onPopBack = onPopBack,
                        onUpdatePoint = onUpdatePoint,
                        onSuccessPoint = onSuccessPoint
                    )
                }

                TAB_STAGE -> {
                    StageTabScreen(
                        pointId = pointId,
                        selectedImageUri = selectedImageUri,
                        onPopBack = onPopBack,
                        onUpdatePoint = onUpdatePoint,
                        onSuccessPoint = onSuccessPoint
                    )
                }

                TAB_NOTE -> {
                    NoteTabScreen(
                        pointId = pointId,
                        selectedImageUri = selectedImageUri,
                        onPopBack = onPopBack,
                        onUpdatePoint = onUpdatePoint,
                        onSuccessPoint = onSuccessPoint
                    )
                }

                else -> {
                    DetailTabScreen(
                        pointId = pointId,
                        selectedImageUri = selectedImageUri,
                        onPopBack = onPopBack,
                        onUpdatePoint = onUpdatePoint,
                        onSuccessPoint = onSuccessPoint
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
    pointId: Long?,
    selectedImageUri: String?,
    onPopBack: () -> Unit,
    onUpdatePoint: (Long?) -> Unit,
    onSuccessPoint: () -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.verticalScroll(scrollState),

        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        if (selectedImageUri != null) {
            PhotoCardComponent(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                path = selectedImageUri
            ) {}
        } else {
            Box(
                modifier = Modifier
                    .size(Spaces.space150)
                    .background(Gray100)
            )
        }
        Text(
            modifier = Modifier.clickable(onClick = { onPopBack.invoke() }),
            text = "DetailScreen $pointId"
        )
        Button(
            modifier = Modifier.fillMaxWidth().padding(Spaces.space16),
            onClick = { onUpdatePoint.invoke(pointId) },
            colors = ButtonDefaults.buttonColors(containerColor = Gray90, contentColor = Black),
            contentPadding = PaddingValues(horizontal = Spaces.space16, vertical = Spaces.space9)
        ) {
            Text(
                text = stringResource(R.string.update_point),
                color = Black,
                fontSize = Sizes.size18
            )
        }

    }
}

@Composable
fun NoteTabScreen(
    pointId: Long?,
    selectedImageUri: String?,
    onPopBack: () -> Unit,
    onUpdatePoint: (Long?) -> Unit,
    onSuccessPoint: () -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text("Note")
    }
}

@Composable
fun StageTabScreen(
    pointId: Long?,
    selectedImageUri: String?,
    onPopBack: () -> Unit,
    onUpdatePoint: (Long?) -> Unit,
    onSuccessPoint: () -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text("Stage")
    }
}

