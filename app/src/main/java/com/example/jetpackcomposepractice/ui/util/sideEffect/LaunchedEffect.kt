package com.example.jetpackcomposepractice.ui.util.sideEffect

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

@Composable
fun LaunchedEffect() {
    val context = LocalContext.current
    //How you use the LaunchEffect(run suspend functions in the scope of a composable)
    LaunchedEffect(key1 = Unit, block = {
        try {
            startTimer(4000L) {
                Toast.makeText(context, "Timer Started", Toast.LENGTH_SHORT).show()
            }
        } catch (ex: Exception) {
            Toast.makeText(context, "Timer Canceled", Toast.LENGTH_SHORT).show()
        }

    })


}