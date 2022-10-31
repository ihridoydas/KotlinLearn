/*
 * Created by hridoydas on 2022/10/12
 * Last modified 10/12/22, 11:27 AM
 * Copyright © 2022 Hridoy Das Inc. All rights reserved.
 */

package com.example.jetpackcomposepractice.lifecycleAndSideEffect.sideEffect

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SideEffect() {
    //First
    //LaunchedEffect()

    //Second
    //RememberCoroutineScope()

    //Third
    //RememberUpdatedState()

    //Fourth
    /*
    DisposableEffect {
        when (it) {
            Lifecycle.Event.ON_CREATE -> {
                //TODO: add What you want
            }
            Lifecycle.Event.ON_START -> {
                //TODO: add What you want
            }
            Lifecycle.Event.ON_RESUME -> {
                //TODO: add What you want
            }
            Lifecycle.Event.ON_PAUSE -> {
                //TODO: add What you want
            }
            Lifecycle.Event.ON_STOP -> {
                //TODO: add What you want
            }
            Lifecycle.Event.ON_DESTROY -> {
                //TODO: add What you want
            }
            Lifecycle.Event.ON_ANY -> {
                //TODO: add What you want
            }

        }
    }

     */
    //Five
    //SideEffect: publish Compose state to non-compose code
    // ComposeCodeToNonComposeCode()

    //Six
    //produceState: convert non-Compose state into Compose state
    NonComposeStateToComposeState(1000)

}

suspend fun startTimer(time: Long, onTimeEnd: () -> Unit) {
    delay(time)
    onTimeEnd()

}

//SideEffect: publish Compose state to non-compose code
@Composable
fun ComposeCodeToNonComposeCode() {
    var timer by remember {
        mutableStateOf(0)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Timer $timer", fontSize = 20.sp)
    }
    SideEffect {
        Thread.sleep(1000)
        timer++

    }
}

//produceState: convert non-Compose state into Compose state
@Composable
fun NonComposeStateToComposeState(timer: Int): State<Int> {
    return produceState(initialValue = 0) {
        while (value < timer) {
            delay(2000)
            value++
        }
    }
}