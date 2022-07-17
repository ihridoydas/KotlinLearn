package com.example.jetpackcomposepractice.navigationPassingDataNavHost

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpackcomposepractice.navigationPassingDataNavHost.ui.theme.JetPackComposePracticeTheme
import com.google.gson.Gson

data class User(
    val id: Int,
    val name: String,
) {
    val description: String
        get() = "$name has an ID of $id"
}

class PassingDataNavHost : ComponentActivity() {

    private val users = listOf(
        User(1, "Hridoy"),
        User(2, "Chandra"),
        User(3, "Das"),
        User(4, "Bithy"),
        User(5, "Rani"),
        User(6, "Das"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposePracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                   AppNavigator()

                }
            }
        }
    }

    //Add Navigator
    @Composable
    fun AppNavigator() {
        val navController = rememberNavController()
        NavHost(navController = navController,
            startDestination = "userView") {
            composable("userView"){ UsersView(navController = navController, users = users)}
            composable(
                "userDetailsView/{user}",
                arguments = listOf(
                    navArgument("user") {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->
                backStackEntry?.arguments?.getString("user")?.let { json ->
                    val user = Gson().fromJson(json, User::class.java)
                    UserDetaisView(user)

                }

            }

        }


    }


}

@Composable
fun UsersView(navController: NavHostController , users: List<User>) {
    fun navigateTouser(users: User){
        val userJson = Gson().toJson(users)
        navController.navigate("userDetailsView/$userJson")
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(users) { user ->
            Card(
                elevation = 4.dp,
                modifier = Modifier
                    .padding(10.dp, 10.dp)
                    .fillMaxSize()
                    .clickable(onClick = { navigateTouser(user) }),
            ) {
                Text(
                    user.name, modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                )
            }
        }

    }

}



@Composable
fun UserDetaisView(user: User) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = user.description)

    }

}