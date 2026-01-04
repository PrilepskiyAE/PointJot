package com.prilepskiy.presentation.uiComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import com.prilepskiy.common.Black
import com.prilepskiy.common.EMPTY_STRING
import com.prilepskiy.common.Gray700
import com.prilepskiy.common.Spaces
import com.prilepskiy.common.TitleTextStyles
import com.prilepskiy.presentation.optional
import com.prilepskiy.presentation.simpleClickable
@Composable
fun ToolbarStandardComponent(
    modifier: Modifier = Modifier,
    title: String = EMPTY_STRING,
    transparentBackButton: Boolean = true,
    iconColor: Color = Gray700,
    textColor: Color = Black,
    onBackPressed: (() -> Unit)? = null,
    firstIcon: ImageVector? = null,
    onSecondClick: (() -> Unit)? = null,
    secondIcon: ImageVector? = null,
    onThirdClick: (() -> Unit)? = null,
    thirdIcon: ImageVector? = null,
) {
    val safeOnBackPressed = onBackPressed?.let { createDebouncedClickHandler(it) }
    val safeOnSecondClick = onSecondClick?.let { createDebouncedClickHandler(it) }
    val safeOnThirdClick = onThirdClick?.let { createDebouncedClickHandler(it) }

    Column(
        modifier = modifier
            .height(Spaces.space64)
            .fillMaxWidth()
            .padding(start = Spaces.space12, end = Spaces.space16)
    ) {
        AppBar(
            modifier = modifier
        ) {
            if (safeOnBackPressed != null && firstIcon != null) {
                Row(
                    modifier = Modifier
                        .padding(top = Spaces.space8, bottom = Spaces.space8)
                        .size(Spaces.space32)
                        .simpleClickable(onClick = safeOnBackPressed)
                        .optional(Modifier.background(White, RoundedCornerShape(Spaces.space12))) {
                            !transparentBackButton
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = firstIcon,
                        contentDescription = null,
                        tint = iconColor,
                        modifier = Modifier.size(Spaces.space24)
                    )
                }

                Spacer(modifier = Modifier.width(Spaces.space12))
            } else {
                Spacer(modifier = Modifier.width(Spaces.space4))
            }

            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(top = Spaces.space8, bottom = Spaces.space8),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (title.isNotEmpty()) {
                    Text(
                        text = title,
                        color = textColor,
                        style = TitleTextStyles.H5_TOOLBAR,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
            }

            if (safeOnSecondClick != null && secondIcon != null) {
                Row(
                    modifier = Modifier.fillMaxHeight(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Row(
                        modifier = Modifier
                            .padding(top = Spaces.space8, bottom = Spaces.space8)
                            .size(Spaces.space32)
                            .simpleClickable(onClick = safeOnSecondClick)
                            .optional(Modifier.background(White, RoundedCornerShape(Spaces.space12))) {
                                !transparentBackButton
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector= secondIcon,
                            contentDescription = null,
                            tint = iconColor,
                            modifier = Modifier.size(Spaces.space24)
                        )
                    }

                    Spacer(modifier = Modifier.width(Spaces.space12))
                }
            }
            if (safeOnThirdClick != null && thirdIcon != null) {
                Row(
                    modifier = Modifier.fillMaxHeight(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Row(
                        modifier = Modifier
                            .padding(top = Spaces.space8, bottom = Spaces.space8)
                            .size(Spaces.space32)
                            .simpleClickable(onClick = safeOnThirdClick)
                            .optional(Modifier.background(White, RoundedCornerShape(Spaces.space12))) {
                                !transparentBackButton
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            thirdIcon,
                            contentDescription = null,
                            tint = iconColor,
                            modifier = Modifier.size(Spaces.space24)
                        )
                    }

                    Spacer(modifier = Modifier.width(Spaces.space12))
                }
            }
        }
    }
}

@Composable
private inline fun AppBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    Column(modifier = modifier.background(Color.Transparent)) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(Spaces.space64),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )
    }
}

@Composable
private fun createDebouncedClickHandler(
    onClick: () -> Unit,
    timeoutMillis: Long = 5555L
): () -> Unit {
    var lastClickTime by remember { mutableStateOf(0L) }

    return {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime > timeoutMillis) {
            lastClickTime = currentTime
            onClick()
        }
    }
}






