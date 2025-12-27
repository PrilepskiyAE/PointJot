package com.prilepskiy.presentation.uiComponent

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.prilepskiy.common.Spaces
import com.prilepskiy.domain.model.CategoryModel
import com.prilepskiy.presentation.R

@Composable
fun ChipsStandardTextComponent(
    modifier: Modifier = Modifier,
    list: List<CategoryModel>,
    colorBackgroundActive: Color,
    colorBackgroundPassive: Color,
    colorTextActive: Color,
    colorTextPassive: Color,
    onClickChip: ((chip: CategoryModel) -> Unit),
    onClickLongChip: ((chip: CategoryModel) -> Unit),
    onClickAdd: (() -> Unit),
) {
    Row(modifier = modifier.horizontalScroll(rememberScrollState())) {
        list.forEachIndexed { index, item ->
            TextChip(
                item = item,
                onClickChips = onClickChip,
                colorBackgroundActive = colorBackgroundActive,
                colorBackgroundPassive = colorBackgroundPassive,
                colorTextActive = colorTextActive,
                colorTextPassive = colorTextPassive,
                onClickLongChips = onClickLongChip
            )
        }
        TextChip(
            CategoryModel(0, stringResource(R.string.main_screen_btn_add)),
            colorBackgroundActive = colorBackgroundActive,
            colorBackgroundPassive = colorBackgroundPassive,
            colorTextActive = colorTextPassive,
            colorTextPassive = colorTextPassive,
            onClickChips = {
                onClickAdd.invoke()
            },
            onClickLongChips = {})
    }
}

@Composable
private fun TextChip(
    item: CategoryModel, onClickChips: ((item: CategoryModel) -> Unit),
    onClickLongChips: (CategoryModel) -> Unit,
    colorBackgroundActive: Color,
    colorBackgroundPassive: Color,
    colorTextActive: Color,
    colorTextPassive: Color,
) {
    Surface(
        modifier = Modifier
            .combinedClickable(
                onClick = {
                    onClickChips.invoke(item)
                },
                onLongClick = {
                    onClickLongChips.invoke(item)
                })
            .padding(Spaces.space4),
        color = when {
            item.isActive -> colorBackgroundActive
            else -> colorBackgroundPassive
        },
        contentColor = when {
            item.isActive -> colorTextActive
            else -> colorTextPassive
        },
        shape = RoundedCornerShape(Spaces.space16),
        border = BorderStroke(
            width = Spaces.space1,
            color = when {
                item.isActive -> colorBackgroundActive
                else -> colorBackgroundPassive
            }
        ),

        ) {
        Row(modifier = Modifier.padding(horizontal = Spaces.space8)) {
            Text(
                text = item.categoryName,
                modifier = Modifier.padding(Spaces.space8)
            )
        }
    }
}