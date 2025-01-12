package com.example.randomuserapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.randomuserapp.presentation.ui.UserList
import com.example.randomuserapp.presentation.ui.UserListDetailScreen

@Composable
fun navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenList.RandomUserDataScreen.route) {
        composable(route = ScreenList.RandomUserDataScreen.route) {
            UserList(navController,onUserClick = { user ->
                navController.navigate("userDetail/${user.name.first}/${user.name.last}/${user.location.city}")
            })
        }
        composable(
           route = "detail/{image}/{name}/{address}",
            arguments = listOf(
                navArgument("image") { type = NavType.StringType },
                navArgument("name") { type = NavType.StringType },
                navArgument("address") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val image = backStackEntry.arguments?.getString("image") ?: ""
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val address = backStackEntry.arguments?.getString("address") ?: ""

            UserListDetailScreen(image = image, name = name, address = address)
        }


    }
}