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
fun NavGraphBuilder.mainNavigate(goToPoint: (Int) -> Unit,goToAddPoint: (Int) -> Unit, popBack: () -> Unit) {
    composable(route = mainRoute) {
        MainScreen(goToPoint=goToPoint, goToAddPoint = goToAddPoint)
    }
    composable(
        route = "$detailRoute/{pointId}",
        arguments = listOf(navArgument("pointId") { type = NavType.IntType })
    ) { navBackStack ->
        DetailScreen(navBackStack.arguments?.getInt("pointId") ?: 0) {
            popBack.invoke()
        }
    }
    composable(
        route = "$addPointRoute/{pointId}",
        arguments = listOf(navArgument("pointId") { type = NavType.IntType })
    ) { navBackStack ->
        AddPointScreen(point=navBackStack.arguments?.getInt("pointId") ?: 0, onPopBack={popBack.invoke()})
    }
}

fun NavController.navigationToDetail(pointId: Int) {
    this.navigate("$detailRoute/${pointId}")
}

fun NavController.navigationToAddPoint(pointId: Int) {
    this.navigate("$addPointRoute/${pointId}")
}