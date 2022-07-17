package com.example.jetpackcomposepractice.nestedNavigation.screens.home

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposepractice.nestedNavigation.BottomBarScreen
import com.example.jetpackcomposepractice.nestedNavigation.graphs.HomeNavGraph

@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {
    Scaffold (
        bottomBar = {

            BottomBar(navController = navController)
        }
            ){
        HomeNavGraph(navController = navController)

    }
    
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens= listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Profile,
        BottomBarScreen.Settings
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currenDestination = navBackStackEntry?.destination


    val bottomBarDestination = screens.any{ it.route ==currenDestination?.route }
    if(bottomBarDestination){
        BottomNavigation {
            screens.forEach{screen->
                AddItem(
                    screen = screen,
                    currentDestination = currenDestination,
                    navController = navController)

            }
            
        }
    }


}


@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}