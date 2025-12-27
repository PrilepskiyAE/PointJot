package com.prilepskiy.presentation

import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


fun Modifier.simpleClickable(enabled: Boolean = true, onClick: () -> Unit): Modifier = composed {
    clickable(
        enabled = enabled,
        onClick = onClick,
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
    )
}

fun Modifier.nullable(modifier: Modifier?): Modifier = if (modifier != null) {
    this.then(modifier)
} else {
    this
}

fun Modifier.optional(
    modifier: Modifier,
    predicate: () -> Boolean,
): Modifier = if (predicate()) {
    this.then(modifier)
} else {
    this
}


fun Modifier.simpleClickableWithClearFocus(
    enabled: Boolean = true,
    onClick: () -> Unit = {},
): Modifier = composed {
    val focusManager = LocalFocusManager.current

    simpleClickable(
        enabled = enabled,
        onClick = {
            focusManager.clearFocus(true)
            onClick()
        }
    )
}

fun Modifier.drawBottomLine(
    thickness: Dp = 1.dp,
    color: Color,
): Modifier = this.drawBehind {
    val strokeWidth = thickness.toPx()
    val y = size.height - strokeWidth / 2

    drawLine(
        color = color,
        start = Offset(0f, y),
        end = Offset(size.width, y),
        strokeWidth = strokeWidth
    )
}

fun Modifier.drawBottomGradientLine(
    thickness: Dp = 1.dp,
    startColor: Color,
    endColor: Color,
): Modifier = this.drawBehind {
    val strokeWidth = thickness.toPx()
    val y = size.height - strokeWidth / 2

    drawLine(
        brush = Brush.horizontalGradient(
            listOf(startColor, endColor)
        ),
        start = Offset(0f, y),
        end = Offset(size.width, y),
        strokeWidth = strokeWidth
    )
}

fun Modifier.drawBottomRoundGradientLine(
    thickness: Dp = 8.dp,
    startColor: Color,
    endColor: Color
): Modifier = this.drawBehind {
    val strokeWidth = thickness.toPx()
    val y = size.height - strokeWidth / 2

    drawRoundRect(
        brush = Brush.horizontalGradient(listOf(startColor, endColor)),
        size = Size(width = this.size.width * 2 / 3, height = 8.dp.toPx()),
        cornerRadius = CornerRadius(x = 36.dp.toPx(), y = 36.dp.toPx()),
        topLeft = Offset(x = 0f, y = this.size.height + 8.dp.toPx())
    )
    drawLine(
        brush = Brush.horizontalGradient(listOf(Color(0x3D222222), Color(0x3D222222))),
        start = Offset(this.size.width * 2 / 3 + 8.dp.toPx(), this.size.height + 12.dp.toPx()),
        end = Offset(size.width, this.size.height + 12.dp.toPx()),
        strokeWidth = 2.dp.toPx()
    )
}

fun Modifier.clearFocusOnKeyboardDismiss(): Modifier = composed {
    val isKeyboardOpen by keyboardIsOpenedAsState()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(isKeyboardOpen) {
        if (!isKeyboardOpen) {
            focusManager.clearFocus()
        }
    }

    this
}

@Composable
fun keyboardIsOpenedAsState(): State<Boolean> {
    val keyboardState = remember { mutableStateOf(false) }
    val view = LocalView.current
    DisposableEffect(view) {
        val onGlobalListener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)
            val screenHeight = view.rootView.height
            val keypadHeight = screenHeight - rect.bottom
            keyboardState.value = keypadHeight > screenHeight * 0.15
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(onGlobalListener)

        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalListener)
        }
    }

    return keyboardState
}
