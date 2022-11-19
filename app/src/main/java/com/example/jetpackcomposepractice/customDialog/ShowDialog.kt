package com.example.jetpackcomposepractice.customDialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DialogSample() {
    var showDialog by remember { mutableStateOf(false) }
    var result by remember { mutableStateOf("Result") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    ) {
        Button(
            onClick = { showDialog = true },
            modifier = Modifier.padding(50.dp)
        ) {
            Text("Show Dialog")
        }
        Text(
            text = result
        )
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                result = "Dismiss"
                showDialog = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        result = "OK"
                        showDialog = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        result = "Cancel"
                        showDialog = false
                    }
                ) {
                    Text("Cancel")
                }
            },
            title = {
                Text("AlertDialog")
            },
            text = {
                Text("これはJetpack Composeのダイアログです。Material3デザインに対応しています。")
            },
        )
    }
}