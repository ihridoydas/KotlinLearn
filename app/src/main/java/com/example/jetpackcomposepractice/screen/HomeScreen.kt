package com.example.jetpackcomposepractice.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposepractice.CustomCircleIndicator

@Composable
fun HomeScreen(
    navController: NavController
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column {
            Text(
                modifier = Modifier.clickable {
                    navController.navigate(
                        route = Screen.Detail.passNameAndId(
                            //if you can pass data (its optional) if u dont pass value its count default value
                            id = 10, name = "Chandra"
                        )
                    )
                },
                text = "Home",
                color = MaterialTheme.colors.primary,
                fontSize = MaterialTheme.typography.h3.fontSize,
                fontWeight = FontWeight.Bold
            )

            CustomCircleIndicator()
        }
    }
}

@Composable
@Preview(showBackground = true)

fun ShowHomeScreen() {
    HomeScreen(navController = rememberNavController())
}