package com.example.jetpackcomposepractice.permissionCheck

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposepractice.permissionCheck.permission.RequestMultiplePermissions
import com.example.jetpackcomposepractice.permissionCheck.ui.theme.JetPackComposePracticeTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi

class PermissionActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposePracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //MultiplePermission()

                    //   RequestPermission(permission = Manifest.permission.READ_CONTACTS)
                    RequestMultiplePermissions(
                        permissions = listOf(
                            Manifest.permission.READ_CONTACTS,
                            Manifest.permission.CAMERA,
                            Manifest.permission.RECORD_AUDIO
                        )
                    )

                }
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

//to use accompanist_version = '0.20.2'
////Multiple Permission without Second Request
//@OptIn(ExperimentalPermissionsApi::class)
//@Composable
//fun MultiplePermission() {
//    val permissonState = rememberMultiplePermissionsState(
//        permissions = listOf(
//            Manifest.permission.RECORD_AUDIO,
//            Manifest.permission.CAMERA,
//        )
//    )
//
//    //Trigger in OnStart in lifecycle
//    val lifecycleOwner = LocalLifecycleOwner.current
//    DisposableEffect(
//        key1 = lifecycleOwner,
//        effect = {
//            val observer = LifecycleEventObserver { _, event ->
//                if (event == Lifecycle.Event.ON_START) {
//                    permissonState.launchMultiplePermissionRequest()
//                }
//
//            }
//            lifecycleOwner.lifecycle.addObserver(observer)
//            onDispose {
//                lifecycleOwner.lifecycle.removeObserver(observer)
//
//            }
//
//        }
//    )
//
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        permissonState.permissions.forEach { perm ->
//            when (perm.permission) {
//                Manifest.permission.CAMERA -> {
//                    when {
//                        perm.hasPermission -> {
//                            Text(text = "Camera permission is accepted")
//
//                        }
//                        perm.shouldShowRationale -> {
//
//                            Text(
//                                text = "Camera permission is Needed" +
//                                        "to access the camera"
//                            )
//                        }
//                        perm.isPermanentDenied() -> {
//
//                            Text(
//                                text = "Camera permission is Permanently " +
//                                        "Denied. You can enable it in the app" +
//                                        "Settings"
//                            )
//                        }
//                    }
//
//                }
//                Manifest.permission.RECORD_AUDIO -> {
//
//                    when {
//                        perm.hasPermission -> {
//                            Text(text = "Record Audio permission is accepted")
//
//                        }
//                        perm.shouldShowRationale -> {
//
//                            Text(
//                                text = "Record Audio permission is Needed" +
//                                        "to access the camera"
//                            )
//                        }
//                        perm.isPermanentDenied() -> {
//                            Text(
//                                text = "Record Audio permission is Permanently " +
//                                        "Denied. You can enable it in the app" +
//                                        "Settings"
//                            )
//                        }
//                    }
//                }
//            }
//
//        }
//
//    }
//
//}