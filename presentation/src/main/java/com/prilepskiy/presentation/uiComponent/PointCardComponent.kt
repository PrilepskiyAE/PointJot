package com.prilepskiy.presentation.uiComponent

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.prilepskiy.common.Blue600
import com.prilepskiy.common.Gray100
import com.prilepskiy.common.Gray80
import com.prilepskiy.common.Green100
import com.prilepskiy.common.Green200
import com.prilepskiy.common.Green500
import com.prilepskiy.common.Red500
import com.prilepskiy.common.Spaces
import com.prilepskiy.domain.model.PointModel
import com.prilepskiy.presentation.R
import com.prilepskiy.presentation.simpleClickable

@Composable
fun PointCardComponent(value: PointModel, goToPoint: (Long) -> Unit) {
    val currenData by remember { mutableLongStateOf(System.currentTimeMillis()) }

    Card(
        shape = RoundedCornerShape(Spaces.space16),
        modifier = Modifier
            .padding(Spaces.space16)
            .fillMaxWidth()
            .height(Spaces.space150)
            .simpleClickable() {
                goToPoint.invoke(value.pointId)
            },
        colors = CardDefaults.cardColors(containerColor = Blue600),
        border = BorderStroke(Spaces.space1, Green100)
    ) {
        Row {
            PhotoCardComponent(path = value.uri, size = Spaces.space150, onClick = {})
            Column(modifier = Modifier.padding(Spaces.space16)) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = Spaces.space8),
                    text = value.pointName,
                    color = Gray100,
                    maxLines = 2

                )
                Surface(
                    color = Gray80,
                    shape = RoundedCornerShape(Spaces.space8),
                    modifier = Modifier
                        .height(Spaces.space1)
                        .fillMaxWidth()
                ) {}
                Spacer(Modifier.height(Spaces.space8))
                when {
                    value.date < currenData -> {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = Spaces.space8),
                            text = "Задача просрочена",
                            color = Red500,
                            maxLines = 1
                        )
                    }

                    else -> {
                        Text(text = "Осталось дней ${try {
                            (value.date - currenData) / 86_400_000
                        } catch (e: Exception) {
                            0L
                        }}")
                        TaskProgressBar(value.colFinished, value.colActive)

                    }
                }

            }
        }

    }
}

fun calculateProgressPercent(completed: Long, total: Long): Float {
    return if (total > 0) {
        (completed.toFloat() / total) * 100
    } else {
        0f
    }
}

@Composable
fun TaskProgressBar(
    completedTasks: Long,
    totalTasks: Long,
    modifier: Modifier = Modifier
) {
    val progress = remember {
        (calculateProgressPercent(completedTasks, totalTasks) / 100f).times(100).toInt()
    }

    Column(modifier = modifier) {

        Text(
            text = "Выполнено: $progress %",
            modifier = Modifier.padding(bottom = Spaces.space8)
        )

        LinearProgressIndicator(
            progress = { if (progress.toFloat() != 0f) (progress.toFloat()/100) else 0f },
            modifier = Modifier.fillMaxWidth(),
            color = Green500,
            trackColor = Green200,
            strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
        )
    }
}
