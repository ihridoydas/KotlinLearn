package com.example.jetpackcomposepractice.permissionAndConnectivityCheck

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposepractice.permissionAndConnectivityCheck.connectivity.ConnectivityObserver
import com.example.jetpackcomposepractice.permissionAndConnectivityCheck.connectivity.NetworkConnectivityObserver
import com.example.jetpackcomposepractice.permissionAndConnectivityCheck.ui.theme.JetPackComposePracticeTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi

class PermissionActivity : ComponentActivity() {

    private lateinit var connectivityObserver: ConnectivityObserver

    @RequiresApi(33)
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityObserver = NetworkConnectivityObserver(applicationContext)
        setContent {
            JetPackComposePracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //MultiplePermission()
                    BottomSheet()
                    //RequestPermission(permission = Manifest.permission.POST_NOTIFICATIONS)
//                    RequestMultiplePermissions(
//                        permissions = listOf(
//                            Manifest.permission.POST_NOTIFICATIONS,
//                            Manifest.permission.READ_CONTACTS,
//                            Manifest.permission.CAMERA,
//                            Manifest.permission.RECORD_AUDIO
//                        )
//                    )

                    //Connectivity check
                    val status by connectivityObserver.observer().collectAsState(
                        initial = ConnectivityObserver.Status.Unavailable
                    )
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        val context = LocalContext.current
                        Text(
                            modifier = Modifier.clickable {
                                Toast.makeText(
                                    context,
                                    "Clickable..............",
                                    Toast.LENGTH_LONG
                                ).show()
                            },
                            text = "Network Status $status"
                        )
                    }


                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    JetPackComposePracticeTheme {
        BottomSheet()
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