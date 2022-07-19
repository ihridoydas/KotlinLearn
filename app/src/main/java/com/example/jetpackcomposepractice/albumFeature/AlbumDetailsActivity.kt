package com.example.jetpackcomposepractice.albumFeature

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposepractice.albumFeature.ui.theme.JetPackComposePracticeTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

class AlbumDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposePracticeTheme {
                val scope = rememberCoroutineScope()
                val navController = rememberNavController()
                val systemUiController = rememberSystemUiController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AlbumDetailScreen(
                        navController = navController,
                        onSelectorChange = {
                            when (it) {
                                InputSelectorAlbumDetail.CAMERA_ADD_VEHICLE_INFO -> {
                                    scope.launch {
                                        startActivity(
                                            Intent(
                                                applicationContext,
                                                AlbumDetailsActivity::class.java
                                            )
                                        )
                                    }
                                }
                                InputSelectorAlbumDetail.CAMERA_ADD_APPEARANCE -> {
                                    scope.launch {
                                        startActivity(
                                            Intent(
                                                applicationContext,
                                                AlbumDetailsActivity::class.java
                                            )
                                        )
                                    }
                                }
                                InputSelectorAlbumDetail.CAMERA_ADD_OTHERS -> {
                                    scope.launch {
                                        startActivity(
                                            Intent(
                                                applicationContext,
                                                AlbumDetailsActivity::class.java
                                            )
                                        )
                                    }
                                }
                                else -> {
//                                            Toast.makeText(
//                                                applicationContext,
//                                                "Not supported. $it",
//                                                Toast.LENGTH_SHORT
//                                            ).show()
                                }
                            } // when
                        }
                    )

                }
            }
        }
    }
}
