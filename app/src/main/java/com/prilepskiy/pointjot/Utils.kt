package com.prilepskiy.pointjot

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

@Composable
fun SetNavigationBarColor() {
    val view = LocalView.current
    val context = view.context

    val activity = context as? Activity ?: return
    val window = activity.window

    WindowCompat.setDecorFitsSystemWindows(window, false)

    val color = ContextCompat.getColor(
        context,
        R.color.blue_600
    )

    window.navigationBarColor = color
}

class DefaultProcessLifecycleObserver(private val onProcessCameForeground:()-> Unit): DefaultLifecycleObserver{
    override fun onStart(owner: LifecycleOwner) {
        onProcessCameForeground()
    }
}