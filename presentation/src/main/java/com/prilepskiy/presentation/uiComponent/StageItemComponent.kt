package com.prilepskiy.presentation.uiComponent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import com.prilepskiy.common.BodyTextStyles
import com.prilepskiy.common.Gray300
import com.prilepskiy.common.Gray80
import com.prilepskiy.common.LabelTextStyles
import com.prilepskiy.common.Spaces
import com.prilepskiy.domain.model.StageModel
import com.prilepskiy.presentation.simpleClickable

@Composable
fun StageItemComponent(
    stageModel: StageModel,
    onSuccessStage: (stageModel: StageModel) -> Unit,
    openStage: (stageModel: StageModel) -> Unit,
    onDeleteStage: (stageModel: StageModel) -> Unit,
) {
    Column() {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = stageModel.isFinish, onCheckedChange = {
                val currentIsActive = stageModel.isFinish
                onSuccessStage.invoke(stageModel.copy(isFinish = !currentIsActive))
            })
            Column(modifier = Modifier
                .weight(1f)
                .simpleClickable() {
                    openStage.invoke(stageModel)
                }) {
                Text(
                    modifier = Modifier.padding(horizontal = Spaces.space16),
                    text = stageModel.title,
                    style = BodyTextStyles.Large,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 8,
                    color = Gray80,
                    textDecoration= if (stageModel.isFinish) TextDecoration.LineThrough else null
                )
                Text(
                    modifier = Modifier.padding(horizontal = Spaces.space16),
                    text = stageModel.label,
                    style = LabelTextStyles.ExtraSmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 8,
                    color = Gray300,
                    textDecoration= if (stageModel.isFinish) TextDecoration.LineThrough else null
                )
            }
            IconButton(onClick = { onDeleteStage.invoke(stageModel) }) {
                Icon(
                    Icons.Filled.Delete,
                    contentDescription = null,
                    tint = Gray80,
                    modifier = Modifier.size(Spaces.space24)
                )
            }
        }
        Surface(
            color = Gray80,
            shape = RoundedCornerShape(Spaces.space8),
            modifier = Modifier
                .height(Spaces.space1)
                .fillMaxWidth()
        ) {}
    }
}