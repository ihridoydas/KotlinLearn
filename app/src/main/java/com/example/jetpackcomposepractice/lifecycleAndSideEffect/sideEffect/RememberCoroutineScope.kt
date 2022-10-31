package com.example.jetpackcomposepractice.lifecycleAndSideEffect.sideEffect

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
fun RememberCoroutineScope() {
    //How you use the rememberCoroutineScope(obtain a composition-aware scope to launch a coroutine outside a composable)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var job: Job? by remember { mutableStateOf(null) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                Toast.makeText(context, "Timer Started", Toast.LENGTH_SHORT).show()
                job = scope.launch {
                    try {
                        startTimer(5000L) {
                            Toast.makeText(context, "Timer Ended", Toast.LENGTH_SHORT).show()
                        }

                    } catch (ex: Exception) {
                        Toast.makeText(context, "Timer Canceled", Toast.LENGTH_SHORT).show()
                    }

                }

            })
        {
            Text(text = "Remember CoroutineScope Start Timer")
        }
        Button(
            onClick = {
                if (job?.isActive == true) {
                    Toast.makeText(context, "cancel Timer start", Toast.LENGTH_SHORT).show()
                    job?.cancel()
                    Toast.makeText(context, "Timer End", Toast.LENGTH_SHORT).show()
                }

            })
        {
            Text(text = "Remember CoroutineScope Cancel Timer")
        }

    }

}