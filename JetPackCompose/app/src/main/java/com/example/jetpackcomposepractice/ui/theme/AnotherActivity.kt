package com.example.jetpackcomposepractice.ui.theme

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposepractice.MainActivity
import com.example.jetpackcomposepractice.ui.theme.ui.theme.JetPackComposePracticeTheme

class AnotherActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Button(onClick = {
                val navigate = Intent(this@AnotherActivity,MainActivity::class.java)
                startActivity(navigate)
            }) {
                Text(text = "Navigate Main Activity")
            }


        }
    }
}
