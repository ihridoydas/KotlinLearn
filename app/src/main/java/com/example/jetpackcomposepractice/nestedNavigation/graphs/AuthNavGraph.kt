package com.example.jetpackcomposepractice.nestedNavigation.graphs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.jetpackcomposepractice.PracticeScreen
import com.example.jetpackcomposepractice.nestedNavigation.screens.LoginContent
import com.example.jetpackcomposepractice.nestedNavigation.screens.ScreenContent
import com.example.jetpackcomposepractice.stickyHeader.model.Sign
import com.example.jetpackcomposepractice.stickyHeader.model.SignGroup


@OptIn(ExperimentalFoundationApi::class)
fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {

        composable(route = AuthScreen.Login.route) {
            LoginContent(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.HOME)
                },
                onSignUpClick = {
                    navController.navigate(AuthScreen.SignUp.route)
                },
                onForgotClick = {
                    navController.navigate(AuthScreen.Forgot.route)
                },
                onPracticeClick = {
                    navController.navigate(AuthScreen.Practice.route)
                }
            )
        }
        composable(route = AuthScreen.SignUp.route) {
            ScreenContent(name = AuthScreen.SignUp.route) {
            }
        }

        composable(route = AuthScreen.Forgot.route) {
            ScreenContent(name = AuthScreen.Forgot.route) {

            }
        }
        composable(route = AuthScreen.Practice.route) {
            ScreenContent(name = AuthScreen.Practice.route) {}
            PracticeScreen {
                // SetSticky()
                //SList()
            }
        }

    }

}

//@Composable
//fun SetSticky() {
//    val scope = rememberCoroutineScope()
//    val isDataLoaded = remember {
//        mutableStateOf(false)
//    }
//    val groups = remember {
//        mutableStateOf(listOf<SignGroup>())
//    }
//    scope.launch(Dispatchers.IO) {
//        isDataLoaded.value = true
//    }
//
//    if (isDataLoaded.value) {
//        StickyList(groups = groups.value)
//    }
//
//}

@Composable
fun HeaderView(text: String) {
    Text(
        text,
        fontSize = 18.sp,
        modifier = Modifier
            .fillMaxWidth()
            .alpha(0.95f)
            .background(Color.LightGray)
            .padding(16.dp)
    )
}

@Composable
fun SignCard(sign: Sign) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .padding(start = 16.dp)
            .fillMaxWidth()
    ) {
        // this method will get asset image and convert to bitmap

        Column(
            modifier = Modifier.padding(top = 16.dp, end = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(sign.name, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Blue)
            Text(sign.title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            sign.description?.let {
                Text(it, fontSize = 14.sp, fontWeight = FontWeight.Light, maxLines = 3)
            }
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StickyList(groups: List<SignGroup>) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        groups.forEach {
            stickyHeader {
                HeaderView(text = it.title)
            }
            items(it.signs.size) { index ->
                Column {
                    SignCard(sign = it.signs[index])
                    Divider()
                }
            }
        }

    }

}


@Composable
fun SList() {
    val groups = listOf(
        SignGroup(
            "Dangerous",
            listOf(
                Sign(
                    "P.105",
                    title = "Bien 105",
                    description = "This is a test description",
                    "P105"
                ),
//                Sign("P.105", title = "Bien 105", description = "This is a test description", "P105"),
                Sign(
                    "P.105",
                    title = "Bien 105",
                    description = "This is a test description",
                    "P105"
                ),
                Sign(
                    "P.105",
                    title = "Bien 105",
                    description = "This is a test description",
                    "P105"
                ),
                Sign(
                    "P.105",
                    title = "Bien 105",
                    description = "This is a test description",
                    "P105"
                ),
                Sign(
                    "P.105",
                    title = "Bien 105",
                    description = "This is a test description",
                    "P105"
                ),
                Sign(
                    "P.105",
                    title = "Bien 105",
                    description = "This is a test description",
                    "P105"
                ),
                Sign(
                    "P.105",
                    title = "Bien 105",
                    description = "This is a test description",
                    "P105"
                ),
                Sign(
                    "P.105",
                    title = "Bien 105",
                    description = "This is a test description",
                    "P105"
                ),
                Sign(
                    "P.105",
                    title = "Bien 105",
                    description = "This is a test description",
                    "P105"
                ),
                Sign(
                    "P.105",
                    title = "Bien 105",
                    description = "This is a test description",
                    "P105"
                ),
                Sign(
                    "P.105",
                    title = "Bien 105",
                    description = "This is a test description",
                    "P105"
                ),
                Sign(
                    "P.105",
                    title = "Bien 105",
                    description = "This is a test description",
                    "P105"
                ),
                Sign(
                    "P.105",
                    title = "Bien 105",
                    description = "This is a test description",
                    "P105"
                )
            )
        ),
        SignGroup(
            "Guideline",
            listOf(
                Sign(
                    "P.105",
                    title = "Bien 105",
                    description = "This is a test description",
                    "P105"
                ),
                Sign(
                    "P.105",
                    title = "Bien 105",
                    description = "This is a test description",
                    "P105"
                ),
                Sign(
                    "P.105",
                    title = "Bien 105",
                    description = "This is a test description",
                    "P105"
                ),
                Sign(
                    "P.105",
                    title = "Bien 105",
                    description = "This is a test description",
                    "P105"
                ),
                Sign(
                    "P.105",
                    title = "Bien 105",
                    description = "This is a test description",
                    "P105"
                ),
                Sign(
                    "P.105",
                    title = "Bien 105",
                    description = "This is a test description",
                    "P105"
                ),
                Sign(
                    "P.105",
                    title = "Bien 105",
                    description = "This is a test description",
                    "P105"
                ),
                Sign(
                    "P.105",
                    title = "Bien 105",
                    description = "This is a test description",
                    "P105"
                ),
                Sign(
                    "P.105",
                    title = "Bien 105",
                    description = "This is a test description",
                    "P105"
                ),
                Sign(
                    "P.105",
                    title = "Bien 105",
                    description = "This is a test description",
                    "P105"
                )
            )
        )
    )
    StickyList(groups = groups)

}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen("LOGIN")
    object SignUp : AuthScreen("SIGN_UP")
    object Forgot : AuthScreen("FORGOT")
    object Practice : AuthScreen("PRACTICE")

}