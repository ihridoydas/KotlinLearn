package com.example.jetpackcomposepractice.learnTesting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposepractice.R
import com.example.jetpackcomposepractice.learnTesting.ui.theme.JetPackComposePracticeTheme

class TestingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposePracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CounterTesting()
                }
            }
        }

    }
}

@Composable
fun CounterTesting() {
    var counter by remember{ mutableStateOf(0) }
    Column {
        Button(
            onClick = { counter++ },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = stringResource(R.string.increment_counter))

        }
        
       Text(text = stringResource(id = R.string.clicks,counter))
    }
}

@Preview(showBackground = true)
@Composable
fun CounterTestingPreview() {
    JetPackComposePracticeTheme {
        CounterTesting()
    }
}