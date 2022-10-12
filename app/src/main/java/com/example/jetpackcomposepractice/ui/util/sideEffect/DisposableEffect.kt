package com.example.jetpackcomposepractice.ui.util.sideEffect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

@Composable
fun DisposableEffect(OnEvent: (event: Lifecycle.Event) -> Unit) {
    val eventHandler = rememberUpdatedState(newValue = OnEvent)
    val lifecycleOwner = rememberUpdatedState(newValue = LocalLifecycleOwner.current)

    androidx.compose.runtime.DisposableEffect(key1 = lifecycleOwner.value) {
        val lifecycle = lifecycleOwner.value.lifecycle
        val observer = LifecycleEventObserver { source, event ->
            eventHandler.value(event)

        }
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }

}

//TODO: When use this-> LifecycleEventListener() for any activity

@Composable
fun ImplementLifecycleEvent() {
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

}
