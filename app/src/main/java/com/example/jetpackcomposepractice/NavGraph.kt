package com.example.jetpackcomposepractice

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun SetupNavGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = Screen.Home.route) {
        composable(
            route = Screen.Home.route
        ){
            HomeScreen(navController = navHostController)
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                    navArgument("id"){
                    type = NavType.IntType
                }
            )

        ){
            Log.d("Check", it.arguments?.getInt("id").toString())
            DetailScreen(navController = navHostController)
        }
    }

}