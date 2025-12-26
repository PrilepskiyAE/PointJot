package com.prilepskiy.pointjot.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.prilepskiy.presentation.detailScreen.DetailScreen
import com.prilepskiy.presentation.mainScreen.MainScreen

const val mainRoute = "main_route"
const val detailRoute = "detail_route"
fun NavGraphBuilder.mainNavigate(goToPoint: (Int) -> Unit, popBack: () -> Unit) {
    composable(route = mainRoute) {
        MainScreen(goToPoint=goToPoint)
    }
    composable(
        route = "$detailRoute/{pointId}",
        arguments = listOf(navArgument("pointId") { type = NavType.IntType })
    ) { navBackStack ->
        DetailScreen(navBackStack.arguments?.getInt("pointId") ?: 0) {
            popBack.invoke()
        }
    }
}

fun NavController.navigationToDetail(pointId: Int) {
    this.navigate("$detailRoute/${pointId}")
}