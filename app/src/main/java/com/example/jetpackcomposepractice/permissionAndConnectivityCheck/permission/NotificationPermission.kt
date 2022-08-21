/*
 * Created by hridoydas on 2022/08/18
 * Last modified 8/18/22, 5:19 PM
 * Copyright © 2022 Cognivision Inc. All rights reserved.
 */

package com.example.jetpackcomposepractice.permissionAndConnectivityCheck.permission
//This code work for that library [Can you this if u customize that] Android 13
//implementation "com.google.accompanist:accompanist-permissions:0.18.0"
/**
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState

/**
 * Handle Notification permissions
*/
@ExperimentalPermissionsApi
@Composable
fun NotificationPermission(
permission: String = android.Manifest.permission.POST_NOTIFICATIONS,
rationale: String = "Please Allow Notification Permission",
permissionNotAvailableContent: @Composable () -> Unit = { },
content: @Composable () -> Unit = { }
) {
val permissionState = rememberPermissionState(permission)
val lifecycleOwner = LocalLifecycleOwner.current

/**
 * スプラッシュ画面の次Notification Permissionダイアログを表示します。
 * LifecycleEventObserver->ON_STARTイベントが実装する
*/
DisposableEffect(
key1 = lifecycleOwner,
effect = {
val observer = LifecycleEventObserver { _, event ->
if (event == Lifecycle.Event.ON_START) {
if (!permissionState.hasPermission) {
permissionState.launchPermissionRequest()
}
}
}
lifecycleOwner.lifecycle.addObserver(observer)
onDispose {
lifecycleOwner.lifecycle.removeObserver(observer)

}

}
)
/**
 *Accompanist Version -> "com.google.accompanist:accompanist-permissions:0.18.0"
 *Version アップデートするとPermissionRequired()のメソッド変えるかも知れません。
*/
PermissionRequired(
permissionState = permissionState,
permissionNotGrantedContent = {
if (permissionState.shouldShowRationale && !permissionState.hasPermission) {
NotificationContent(
onClick = { permissionState.launchPermissionRequest() }
)
}
},
permissionNotAvailableContent = {
permissionNotAvailableContent()
NotificationPermissionDeniedContent(
shouldShowRationale = permissionState.shouldShowRationale,
onRequestPermission = {
if (permissionState.shouldShowRationale && !permissionState.hasPermission) {
permissionState.launchPermissionRequest()
}
}
)
},
content = content
)
}

/**
 * ２回以上ダイアログ「Dont`allow 」すると直接Setting 行ってPermissionしないといけないため」
 * も一度ダイアログを表示します、Settingブタン→App Info に行きます。
 * Cancelブタン→ Permissionしないで次に続けるとできる、
 * 「Cancelブタンなし」それもできます。そうしたらUserがSetting に行ってPermission→Allowするしかない。
**/

@Composable
fun GotoSettingsDialog(
title: String,
message: String,
onSettingsTapped: () -> Unit,
onDismiss: () -> Unit
) {
AlertDialog(
onDismissRequest = {/* Don't */ },
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

/**
 * GotoSettingsDialog() の内容書いてあります。
**/

@Composable
fun NotificationContent(showButton: Boolean = true, onClick: () -> Unit) {
var showDialog by remember { mutableStateOf(true) }
Column(
modifier = Modifier
.fillMaxSize()
.padding(50.dp),
verticalArrangement = Arrangement.Center,
horizontalAlignment = Alignment.CenterHorizontally
) {
if (showButton) {
/**
 * Android 13以下でしたらNotification permission必要ないのでPermissionダイアログを表示しない。
**/
if (Build.VERSION.SDK_INT >= 33) {
val context = LocalContext.current
if (showDialog) {
GotoSettingsDialog(
title = "Allow Notification permission",
message = "Please allow Notification Permission",
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

/**
 * 「Don`t Allow」する場合
**/
@ExperimentalPermissionsApi
@Composable
fun NotificationPermissionDeniedContent(
shouldShowRationale: Boolean,
onRequestPermission: () -> Unit
) {
if (Build.VERSION.SDK_INT >= 33) {
if (!shouldShowRationale) {
NotificationContent(
onClick = onRequestPermission
)
}
}
}
 */