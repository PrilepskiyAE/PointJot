package com.prilepskiy.pointjot.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.prilepskiy.pointjot.navigation.mainNavigate
import com.prilepskiy.pointjot.navigation.mainRoute
import com.prilepskiy.pointjot.navigation.navigationToAddPoint
import com.prilepskiy.pointjot.navigation.navigationToDetail

@Composable
fun RootScreen(
    modifier: Modifier = Modifier,
    startDestination: String = mainRoute,
) {
    val rootNavController: NavHostController = rememberNavController()
    NavHost(
        navController = rootNavController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        mainNavigate(
            goToPoint = { pointId -> rootNavController.navigationToDetail(pointId) },
            goToAddPoint = {pointId -> rootNavController.navigationToAddPoint(pointId)},
            popBack = { rootNavController.popBackStack() })
    }
}