package com.example.jetpackcomposepractice.permissionAndConnectivityCheck.permission

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.*


@ExperimentalPermissionsApi
@Composable
fun RequestPermission(
    permission: String,
    rationale: String = "To use this app's functionalities, you need to give us the permission.",
    permissionNotAvailableContent: @Composable () -> Unit = { },
    content: @Composable () -> Unit = { }
    //deniedMessage: String = "Give this app a permission to proceed. If it doesn't work, then you'll have to do it manually from the settings.",

) {
    val permissionState = rememberPermissionState(permission)
    val lifecycleOwner = LocalLifecycleOwner.current
    var per: Unit? = null

    DisposableEffect(
        key1 = lifecycleOwner,
        effect = {
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_START) {
                    per = permissionState.launchPermissionRequest()
                }

            }
            lifecycleOwner.lifecycle.addObserver(observer)
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)

            }

        }
    )

    PermissionRequired(
        permissionState = permissionState,
        permissionNotGrantedContent = { shouldShowRationale ->
            PermissionDeniedContent(
                deniedMessage = "Give this app a permission to proceed. If it doesn't work, then you'll have to do it manually from the settings.",
                rationaleMessage = rationale,
                shouldShowRationale = shouldShowRationale,
                onRequestPermission = {
                    per = permissionState.launchPermissionRequest()
                }
            )
        },
        permissionNotAvailableContent = permissionNotAvailableContent,
        content = {
            content()
//            Content(
//                text = "PERMISSION GRANTED!",
//                showButton = false
//            ) {}
        }
    )
}

@ExperimentalPermissionsApi
@Composable
private fun PermissionRequired(
    permissionState: PermissionState,
    permissionNotGrantedContent: @Composable (Boolean) -> Unit,
    permissionNotAvailableContent: @Composable () -> Unit = { },
    content: @Composable () -> Unit
) {
    when (permissionState.status) {
        is PermissionStatus.Granted -> {
            content()
        }
        is PermissionStatus.Denied -> {
            permissionNotAvailableContent()
            permissionNotGrantedContent(permissionState.status.shouldShowRationale)
        }
    }
}


@Composable
fun ShowGotoSettingsDialog(
    title: String,
    message: String,
    onSettingsTapped: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Text(
                text = message,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold
            )
        },
        buttons = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 12.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                Row {
                    Text(
                        text = "Cancel",
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                            .clickable { onDismiss() },
                        style = MaterialTheme.typography.button,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(
                        text = "Setting",
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                            .clickable { onSettingsTapped() },
                        style = MaterialTheme.typography.button,
                        fontWeight = FontWeight.Bold
                    )
                }

            }
        },

        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = false)
    )
}


@Composable
fun Content(text: String, showButton: Boolean = true, onClick: () -> Unit) {
    var showDialog by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(12.dp))
        if (showButton) {
//            Button(onClick = onClick) {
//                Text(text = "Request")
//            }
            if (Build.VERSION.SDK_INT >= 33) {
                val context = LocalContext.current
                if (showDialog) {
                    ShowGotoSettingsDialog(
                        title = "Allow permission",
                        message = "Please allow Permission",
                        onSettingsTapped = {
                            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                data = Uri.parse("package:" + context.packageName)
                                context.startActivity(this)
                            }
                        },
                        onDismiss = {
                            showDialog = false
                        }
                    )
                }

            }

        }
    }
}

@ExperimentalPermissionsApi
@Composable
fun PermissionDeniedContent(
    deniedMessage: String,
    rationaleMessage: String,
    shouldShowRationale: Boolean,
    onRequestPermission: () -> Unit
) {
    if (shouldShowRationale) {
        AlertDialog(
            onDismissRequest = {},
            title = {
                Text(
                    text = "Permission Request",
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.h6.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                )
            },
            text = {
                Text(rationaleMessage)
            },
            confirmButton = {
                Button(onClick = onRequestPermission) {
                    Text("Give Permission")
                }
            }
        )
    } else {
        //  Content(text = deniedMessage, onClick = onRequestPermission)
        Content(
            text = if (Build.VERSION.SDK_INT >= 33) {
                deniedMessage
            } else {
                "Notification by Default Granted For Android 12 and lower"
            }, onClick = onRequestPermission
        )
    }

}

