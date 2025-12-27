package com.prilepskiy.pointjot

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.prilepskiy.pointjot.ui.screen.RootScreen
import com.prilepskiy.pointjot.ui.theme.PointJotTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PointJotTheme {
                SetNavigationBarColor()
                RootScreen()
            }
        }
    }
}

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