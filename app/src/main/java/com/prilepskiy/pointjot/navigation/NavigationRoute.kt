package com.prilepskiy.pointjot.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.prilepskiy.presentation.addScreen.AddPointScreen
import com.prilepskiy.presentation.detailScreen.DetailScreen
import com.prilepskiy.presentation.mainScreen.MainScreen

const val mainRoute = "main_route"
const val detailRoute = "detail_route"
const val addPointRoute = "add_point_route"
const val pointIdArg = "pointId"

fun NavGraphBuilder.mainNavigate(
    goToPoint: (Long) -> Unit,
    goToAddPoint: (Long) -> Unit,
    popBack: () -> Unit
) {
    composable(route = mainRoute) {
        MainScreen(goToPoint = goToPoint, goToAddPoint = goToAddPoint)
    }
    composable(
        route = "$detailRoute/{$pointIdArg}",
        arguments = listOf(navArgument(pointIdArg) { type = NavType.LongType })
    ) { navBackStack ->
        DetailScreen(
            navBackStack.arguments?.getLong(pointIdArg),
            onPopBack = {
                popBack.invoke()
            }, onUpdatePoint = { point ->
                point?.let {
                    goToAddPoint(it)
                }
            })
    }
    composable(
        route = "$addPointRoute/{$pointIdArg}",
        arguments = listOf(navArgument(pointIdArg) { type = NavType.LongType })
    ) { navBackStack ->
        AddPointScreen(
            point = navBackStack.arguments?.getLong(pointIdArg),
            onPopBack = { popBack.invoke() })
    }
}

fun NavController.navigationToDetail(pointId: Long) {
    this.navigate("$detailRoute/${pointId}")
}

fun NavController.navigationToAddPoint(pointId: Long) {
    this.navigate("$addPointRoute/${pointId}")
}