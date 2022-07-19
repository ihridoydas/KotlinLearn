package com.example.jetpackcomposepractice.albumFeature

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.jetpackcomposepractice.albumFeature.util.COLOR_BLUE
import com.example.jetpackcomposepractice.albumFeature.util.ColorHex

/**
　* 取り込み完了ダイアログ
　*/
@Composable
fun ImportCompleteDialog(
    viewModel: AlbumDetailViewModel = AlbumDetailViewModel()
) {

    if (viewModel.showImportCompleteAlertDialog.value) {
        AlertDialog(
            onDismissRequest = { },
            title = { },
            text = {
                Text("取り込みが完了しました")
            },
            confirmButton = {},
            dismissButton = {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        viewModel.onImportCompleteDialogDismiss()
                    },
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = ColorHex.hex2Rgb(COLOR_BLUE),
                        disabledContentColor = Color.Transparent
                    )
                ) {
                    Text("OK")
                }
            }
        )
    }
}
