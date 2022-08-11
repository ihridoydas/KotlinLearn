package com.example.jetpackcomposepractice.permissionCheck

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

@OptIn(ExperimentalPermissionsApi::class)
fun PermissionState.isPermanentDenied(): Boolean {
    return !shouldShowRationale && !hasPermission
}