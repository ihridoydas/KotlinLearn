package com.example.jetpackcomposepractice.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposepractice.UseSwitch
import com.example.jetpackcomposepractice.screen.ui.theme.JetPackComposePracticeTheme

class NavGraphActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposePracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxHeight(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        UseSwitch()

                        //Learn Navigation controller
                        navController = rememberNavController()
                        SetupNavGraph(navHostController = navController)

                        //ScreenSwitch()

                    }
                    }

            }
        }
    }
}

