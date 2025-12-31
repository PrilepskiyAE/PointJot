package com.prilepskiy.presentation.addScreen

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.prilepskiy.common.Blue600
import com.prilepskiy.common.DEFAULT_LONG
import com.prilepskiy.common.Gray80
import com.prilepskiy.common.LabelTextStyles
import com.prilepskiy.common.Spaces
import com.prilepskiy.common.saveImageAndUpdateDb
import com.prilepskiy.domain.model.CategoryModel
import com.prilepskiy.presentation.R
import com.prilepskiy.presentation.uiComponent.CategorySpinnerComponent
import com.prilepskiy.presentation.uiComponent.DatePickerComponent
import com.prilepskiy.presentation.uiComponent.EmptyPhotoCardComponent
import com.prilepskiy.presentation.uiComponent.ErrorMessageComponent
import com.prilepskiy.presentation.uiComponent.InputFieldComponent
import com.prilepskiy.presentation.uiComponent.LoadingComponent
import com.prilepskiy.presentation.uiComponent.PhotoCardComponent
import com.prilepskiy.presentation.uiComponent.ToolbarStandardComponent


@Composable
fun AddPointScreen(
    point: Long?,
    onPopBack: () -> Unit,
    viewModel: AddPointViewModel = hiltViewModel()
) {
    val state = viewModel.viewState

    LaunchedEffect(point) {
        viewModel.onIntent(
            AddPointIntent.InitPoint(point)
        )
    }
    if (state.isLoading) {
        LoadingComponent()
    } else if (!state.error.isNullOrEmpty()) {
        ErrorMessageComponent(textError = state.error) {
        }
    } else {
        AddPointScreen(
            point,
            onPopBack = onPopBack,
            selectedImageUri = state.selectedImageUri,
            pointName = state.pointName,
            motivation = state.motivation,
            reward = state.reward,
            date = state.date,
            categoryList = state.categoryList,
            selectedCategory = state.selectedCategory,
            changeCategory = {
                viewModel.onIntent(AddPointIntent.ChangeCategory(it))
            },
            saveUri = { path ->
                path?.let { viewModel.onIntent(AddPointIntent.ChangePhotoPatch(it)) }
            },
            changePointName = {
                viewModel.onIntent(AddPointIntent.ChangePointName(it))
            },
            changeMotivation = {
                viewModel.onIntent(AddPointIntent.ChangeMotivation(it))
            },
            changeReward = {
                viewModel.onIntent(AddPointIntent.ChangeReward(it))
            },
            changeDate = {
                viewModel.onIntent(AddPointIntent.ChangeDate(it))
            },
            saveOnClick = {
                viewModel.onIntent(AddPointIntent.OnClickSave(point))
                onPopBack.invoke()
            })
    }
}

@Composable
private fun AddPointScreen(
    pointId: Long?,
    selectedImageUri: String?,
    pointName: String,
    motivation: String,
    reward: String,
    date: Long,
    categoryList: List<CategoryModel>,
    selectedCategory: CategoryModel?,
    changeCategory: (CategoryModel) -> Unit,
    saveOnClick: () -> Unit,
    saveUri: (path: String?) -> Unit,
    changePointName: (value: String) -> Unit,
    changeMotivation: (value: String) -> Unit,
    changeReward: (value: String) -> Unit,
    changeDate: (value: Long) -> Unit,
    onPopBack: () -> Unit
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri -> if (uri != null) saveImageAndUpdateDb(context, uri, saveUri = saveUri) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            galleryLauncher.launch("image/*")
        } else {
            Toast.makeText(
                context,
                context.getString(R.string.permisshen_message),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun openGallery() {
        val permission = if (Build.VERSION.SDK_INT >= 33) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }

        val hasPermission = ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED

        if (hasPermission) {
            galleryLauncher.launch("image/*")
        } else {
            permissionLauncher.launch(permission)
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Blue600)
            .verticalScroll(scrollState),
    ) {
        ToolbarStandardComponent(
            modifier = Modifier.padding(vertical = Spaces.space10),
            title = if (pointId == DEFAULT_LONG || pointId == null) stringResource(R.string.create_point) else stringResource(
                R.string.update_point
            ),
            onBackPressed = onPopBack,
            textColor = Gray80,
            iconColor = Gray80,
            firstIcon = Icons.Default.ArrowBack,
            secondIcon = Icons.Default.Check,
            onSecondClick = saveOnClick
        )
        if (selectedImageUri != null) {
            PhotoCardComponent(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                path = selectedImageUri
            ) {
                openGallery()
            }
        } else {
            EmptyPhotoCardComponent(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                openGallery()
            }
        }

        InputFieldComponent(
            modifier = Modifier.padding(vertical = Spaces.space6),
            label = stringResource(R.string.input1),
            textValue = pointName,
            placeholder = stringResource(R.string.label1),
            onValueChange = changePointName,
        )
        InputFieldComponent(
            modifier = Modifier.padding(vertical = Spaces.space6),
            label = stringResource(R.string.input2),
            textValue = motivation,
            placeholder = stringResource(R.string.label2),
            onValueChange = changeMotivation,
        )
        InputFieldComponent(
            modifier = Modifier.padding(vertical = Spaces.space6),
            label = stringResource(R.string.input3),
            textValue = reward,
            placeholder = stringResource(R.string.label3),
            onValueChange = changeReward,
        )
        DatePickerComponent(
            modifier = Modifier.padding(vertical = Spaces.space6),
            selectedDate = date,
            onValueChange = changeDate
        )
        Text(
            modifier = Modifier.padding(horizontal = Spaces.space16),
            text = stringResource(R.string.input4),
            style = LabelTextStyles.Small,
            color = Gray80
        )
        CategorySpinnerComponent(
            categoryList = categoryList,
            selectedCategory = selectedCategory,
            onCategorySelected = changeCategory
        )
    }
}

