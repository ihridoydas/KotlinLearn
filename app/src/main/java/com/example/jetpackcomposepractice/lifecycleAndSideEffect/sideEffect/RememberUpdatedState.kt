package com.example.jetpackcomposepractice.lifecycleAndSideEffect.sideEffect

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RememberUpdatedState() {
    var buttonText by remember { mutableStateOf("Unknown") }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { buttonText = "First Button" },
            colors = ButtonDefaults.buttonColors(Color.Green)
        ) {
            Text(text = "RememberUpdatedState First")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { buttonText = "Second Button" },
            colors = ButtonDefaults.buttonColors(Color.Red)
        ) {
            Text(text = "RememberUpdatedState Second")
        }
        UpdateTimer(buttonText = buttonText)
    }

}

@Composable
fun UpdateTimer(buttonText: String) {
    val updateButtonText by rememberUpdatedState(newValue = buttonText)
    val timerDuration = 6000L
    println("Composing timer with text : $buttonText")

    LaunchedEffect(key1 = Unit, block = {
        startTimer(timerDuration) {
            println("Timer Ended")
            println("Last Button text was $buttonText")
            println("Last Button text using rememberUpdatedState : $updateButtonText")
        }
    })

}