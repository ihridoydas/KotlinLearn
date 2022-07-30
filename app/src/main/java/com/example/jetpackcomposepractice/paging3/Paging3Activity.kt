package com.example.jetpackcomposepractice.paging3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.example.jetpackcomposepractice.paging3.navigation.SetupNavGraph
import com.example.jetpackcomposepractice.paging3.ui.theme.JetPackComposePracticeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Paging3Activity : ComponentActivity() {
    @OptIn(ExperimentalPagingApi::class, ExperimentalCoilApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposePracticeTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Paging3Preview() {
    JetPackComposePracticeTheme {

    }
}