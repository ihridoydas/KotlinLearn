package com.example.jetpackcomposepractice.permissionAndConnectivityCheck

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposepractice.customDialog.MainContentCustomDialog
import com.example.jetpackcomposepractice.permissionAndConnectivityCheck.connectivity.ConnectivityObserver
import com.example.jetpackcomposepractice.permissionAndConnectivityCheck.connectivity.NetworkConnectivityObserver
import com.example.jetpackcomposepractice.permissionAndConnectivityCheck.dialog.CustomDialog
import com.example.jetpackcomposepractice.permissionAndConnectivityCheck.modalTransitionDialog.BottmSheetDialog
import com.example.jetpackcomposepractice.permissionAndConnectivityCheck.permission.RequestMultiplePermissions
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
                    //------------------------------------

                    //Bottom Sheet
                    BottomSheet()

                    //RequestPermission(permission = Manifest.permission.POST_NOTIFICATIONS)
                    if (Build.VERSION.SDK_INT >= 33) {
                        RequestMultiplePermissions(
                            permissions = listOf(
                                Manifest.permission.POST_NOTIFICATIONS,
                            )
                        )
                    }


                    //Connectivity check
                    val status by connectivityObserver.observer().collectAsState(
                        initial = ConnectivityObserver.Status.Unavailable
                    )
                    Box(
                        modifier = Modifier.fillMaxWidth(),
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
                    MainContentCustomDialog()

                    //Simple Call back Alert Button
                    AlertButton()
                    //------------------------------------


//                    Dialog(
//                        content = {
//                            Text(text = "Heelo")
//                        }
//                    )
//

                    //---------------------------------------------
//                    var showDialog by remember { mutableStateOf(false) }
//                    Button(onClick = {
//                        showDialog = true
//                    }) {
//                        Text("Show Dialog")
//                    }
//                    if (showDialog) {
//                        BottomSheetDialog(onDismissRequest = {
//                            showDialog = false
//                        }) {
//                            //Your content
//                        }
//                    }
//                    //---------------------------------------------


                    //Custom Dialog 
//                    Column {
//                        ShowCustomDialog()
//
//                    }

                    //MultiplePermission()


//                    if (showDialog) {
//                        BottomSheetDialog(
//                            onDismissRequest = {
//                                showDialog = false
//                            },
//                            BottomSheetDialogProperties(
//                                dismissOnBackPress = false,
//                                dismissOnClickOutside = false
//                            )
//                        ) {
//                            Column(
//                                modifier = Modifier.padding(
//                                    horizontal = 8.dp,
//                                    vertical = 4.dp
//                                ),
//                                verticalArrangement = Arrangement.spacedBy(16.dp)
//                            ) {
//                                Image(
//                                    painterResource(androidx.compose.foundation.layout.R.drawable.ic_launcher_background),
//                                    contentDescription = "",
//                                )
//                                Button(onClick = { showDialog = false }) {
//                                    androidx.compose.material.Text(text = "Close")
//                                }
//                                androidx.compose.material.Text("Hello there")
//                                androidx.compose.material.Text("General Kenobi")
//                            }
//                        }
//                    }

                }
            }
        }
    }
}


@Composable
private fun AlertButton() {
    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value) {
        alert(msg = "Hello, this is an alert dialog!",
            showDialog = showDialog.value,
            onDismiss = { showDialog.value = false })
    }
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Text("Click the button to show an alert!")
        Button(
            modifier = Modifier,
            onClick = { showDialog.value = true }
        ) {
            Text("Click")
        }
    }
}

@Composable
fun alert(
    msg: String,
    showDialog: Boolean,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            title = {
                Text(msg)
            },
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(onClick = onDismiss) {
                    Text("Dismiss")
                }
            },
            dismissButton = {}
        )
    }
}


@Composable
fun ShowCustomDialog() {

    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value)
        CustomDialog(value = "", setShowDialog = {
            showDialog.value = it
        }) {
            Log.i("HomePage", "HomePage : $it")
        }

//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(text = "Home")
//                }
//            )
//        }) {
    Box(modifier = Modifier.background(Color.White)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = {
                showDialog.value = true
            }) {
                Text(text = "Open Dialog")
            }
            BottmSheetDialog()

            BottomSheet()
        }
    }
    //}
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    JetPackComposePracticeTheme {
        //BottomSheet()
        AlertButton()
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


