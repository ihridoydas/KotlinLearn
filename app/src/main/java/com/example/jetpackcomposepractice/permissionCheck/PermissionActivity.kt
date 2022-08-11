package com.example.jetpackcomposepractice.permissionCheck

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.jetpackcomposepractice.permissionCheck.ui.theme.JetPackComposePracticeTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
class PermissionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposePracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MultiplePermission()
                }
                //
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    JetPackComposePracticeTheme {

    }
}


//Multiple Permission without Second Request
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MultiplePermission() {
    val permissonState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA,
        )
    )

    //Trigger in OnStart in lifecycle
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(
        key1 = lifecycleOwner,
        effect = {
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_START) {
                    permissonState.launchMultiplePermissionRequest()
                }

            }
            lifecycleOwner.lifecycle.addObserver(observer)
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)

            }

        }
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        permissonState.permissions.forEach { perm ->
            when (perm.permission) {
                Manifest.permission.CAMERA -> {
                    when {
                        perm.hasPermission -> {
                            Text(text = "Camera permission is accepted")

                        }
                        perm.shouldShowRationale -> {

                            Text(
                                text = "Camera permission is Needed" +
                                        "to access the camera"
                            )
                        }
                        perm.isPermanentDenied() -> {

                            Text(
                                text = "Camera permission is Permanently " +
                                        "Denied. You can enable it in the app" +
                                        "Settings"
                            )
                        }
                    }

                }
                Manifest.permission.RECORD_AUDIO -> {

                    when {
                        perm.hasPermission -> {
                            Text(text = "Record Audio permission is accepted")

                        }
                        perm.shouldShowRationale -> {

                            Text(
                                text = "Record Audio permission is Needed" +
                                        "to access the camera"
                            )
                        }
                        perm.isPermanentDenied() -> {
                            Text(
                                text = "Record Audio permission is Permanently " +
                                        "Denied. You can enable it in the app" +
                                        "Settings"
                            )
                        }
                    }
                }
            }

        }

    }

}