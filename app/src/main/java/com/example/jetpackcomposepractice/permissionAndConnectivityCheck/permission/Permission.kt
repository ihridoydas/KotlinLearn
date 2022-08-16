package com.example.jetpackcomposepractice.permissionAndConnectivityCheck.permission

/**
 * Created by DavidA on 2022/05/17.
 * Copyright © 2022 Cognivision inc. All rights reserved.
 */

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.google.accompanist.permissions.*

/**
 * Handle camera permissions
 */
@ExperimentalPermissionsApi
@Composable
fun Permission(
    permission: String = android.Manifest.permission.CAMERA,
    rationale: String = "To use this app's functionalities, you need to give us the permission.",
    permissionNotAvailableContent: @Composable () -> Unit = { },
    content: @Composable () -> Unit = { },

    ) {
    var permissionAlreadyRequested by rememberSaveable {
        mutableStateOf(false)
    }
    val permissionState = rememberPermissionState(permission) {
        permissionAlreadyRequested = true
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
                                .padding(vertical = 12.dp),
                            // .clickable { onDismiss() },
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

    /**
     * Permission alert dialog
     */
    @Composable
    fun Rationale(
        text: String,
        onRequestPermission: () -> Unit
    ) {
        AlertDialog(
            onDismissRequest = { /* Don't */ },
            title = {
                Text(text = "Permission request")
            },
            text = {
                Text(text)
            },
            confirmButton = {

                Button(onClick = onRequestPermission) {
                    Text("Ok")
                }


//                Button(onClick = onRequestPermission) {
//                    if(!permissionAlreadyRequested && !permissionState.status.shouldShowRationale){
//                        SideEffect {
//                            permissionState.launchPermissionRequest()
//                        }
//                    }else if(permissionState.status.shouldShowRationale){
//                        permissionState.launchPermissionRequest()
//
//                    }else{
//                        val context = LocalContext.current
//                        var showDialog by remember { mutableStateOf(true) }
//                        if(showDialog){
//                            ShowGotoSettingsDialog (
//                                title = "Allow permission",
//                                message = "Please allow Permission",
//                                onSettingsTapped = {
//                                    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
//                                        data = Uri.parse("package:" + context.packageName)
//                                        context.startActivity(this)
//                                    }
//                                },
//                            onDismiss = {
//                                showDialog = false
//                            }
//                            )
//                        }
//
//                    }
//                }

            }
        )
    }


    HandleRequest(
        permissionState = permissionState,
        deniedContent = { shouldShowRationale ->
            Rationale(
                text = rationale,
                onRequestPermission = { permissionState.launchPermissionRequest() }
            )
        },
        permissionNotAvailableContent = permissionNotAvailableContent,
        content = content
    )

//    PermissionRequired(
//        permissionState = permissionState,
//        permissionNotGrantedContent = {
//            Rationale(
//                text = rationale,
//                onRequestPermission = { permissionState.launchPermissionRequest() }
//            )
//        },
//        permissionNotAvailableContent = permissionNotAvailableContent,
//        content = content
//    )
}

//____________


@ExperimentalPermissionsApi
@Composable
private fun HandleRequest(
    permissionState: PermissionState,
    deniedContent: @Composable (Boolean) -> Unit,
    permissionNotAvailableContent: @Composable () -> Unit = { },
    content: @Composable () -> Unit
) {
    when (permissionState.status) {
        is PermissionStatus.Granted -> {
            content()
        }
        is PermissionStatus.Denied -> {
            deniedContent(permissionState.status.shouldShowRationale)
        }
    }
}


//____________
