package com.example.jetpackcomposepractice.paging3.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.example.jetpackcomposepractice.paging3.screen.home.HomeScreen
import com.example.jetpackcomposepractice.paging3.screen.search.SearchScreen

@ExperimentalCoilApi
@OptIn(ExperimentalPagingApi::class)
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.Search.route) {
            SearchScreen(navController = navController)
        }
    }
}