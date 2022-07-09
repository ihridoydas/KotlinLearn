package com.example.jetpackcomposepractice.TODO.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.example.jetpackcomposepractice.ui.theme.JetPackComposePracticeTheme

class TodoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetPackComposePracticeTheme {
                Surface(color = MaterialTheme.colors.background){

                }
            }
        }
    }
}