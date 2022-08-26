package com.example.jetpackcomposepractice.customDialog

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext


//Custom Dialog with Picture

@Composable
fun MainContentCustomDialog() {
    val context = LocalContext.current
    var showCustomDialog by remember {
        mutableStateOf(false)
    }
    var showLoadingDialog by remember {
        mutableStateOf(false)
    }

    var showInputDialog by remember {
        mutableStateOf(false)
    }

    Column(
        Modifier.fillMaxWidth(),
        Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { showCustomDialog = !showCustomDialog }, Modifier.wrapContentSize()) {
            Text(text = "Show Alert Dialog")
        }

        Button(onClick = { showLoadingDialog = !showLoadingDialog }, Modifier.wrapContentSize()) {
            Text(text = "Show Loading Dialog")
        }

        Button(onClick = { showInputDialog = !showInputDialog }, Modifier.wrapContentSize()) {
            Text(text = "Show Input Dialog")
        }
    }

    if (showCustomDialog) {
        CustomAlertDialog({
            showCustomDialog = !showCustomDialog
        }, {
            val activity = (context as? Activity)
            activity?.finish()
        })
    }

    if (showLoadingDialog) {
        LoadingView {
            showLoadingDialog = !showLoadingDialog
        }
    }

    if (showInputDialog) {
        InputDialogView {
            showInputDialog = !showInputDialog
        }
    }
}
