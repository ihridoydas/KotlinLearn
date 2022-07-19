package com.example.jetpackcomposepractice.albumFeature

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposepractice.albumFeature.util.*

/**
 * 追加撮影アラートダイアログ
 * @param viewModel アルバム一覧画面のViewModel
 * @param launcher Photo picker
 */
@Composable
@Preview(showBackground = true)
fun AddPictureOtherAlertDialog(
    viewModel: AlbumDetailViewModel = AlbumDetailViewModel(),
    launcher: ManagedActivityResultLauncher<String, Uri?>? = null,
    onSelectorChange: (InputSelectorAlbumDetail) -> Unit = {}
) {

    if (viewModel.showAddPictureOtherAlertDialog.value) {
        AlertDialog(
            modifier = Modifier.testTag("ALBUM_DETAIL_ADD_PICTURE_OTHER_ALERT"),
            onDismissRequest = { },
            text = {
                Column {
                    Text(ADD_PICTURE)
                    Text(NUMBER_OF_PICTURE_LEFT)
                }
            },

            buttons = {
                Column {
                    Row(
                        modifier = Modifier.padding(all = 0.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        // 撮影するボタン
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                onSelectorChange(InputSelectorAlbumDetail.CAMERA_ADD_OTHERS)
                                viewModel.onAddPictureOtherDialogConfirmShoot()
                            },
                            colors = ButtonDefaults.textButtonColors(
                                containerColor = Color.Transparent,
                                contentColor = ColorHex.hex2Rgb(COLOR_BLUE),
                                disabledContentColor = Color.Transparent
                            )
                        ) {
                            Text(TAKE_PICTURE)
                        }
                    }

                    // カメラロールから取り込むボタン
                    Row(
                        modifier = Modifier.padding(all = 0.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                viewModel.onAddPictureOtherDialogConfirmImport()
                                // Launch photo picker
                                if (launcher != null) {
                                    launcher!!.launch("image/*")
                                }
                            },
                            colors = ButtonDefaults.textButtonColors(
                                containerColor = Color.Transparent,
                                contentColor = ColorHex.hex2Rgb(COLOR_BLUE),
                                disabledContentColor = Color.Transparent
                            )
                        ) {
                            Text(IMPORT_FROM_CAMERA_ROLL)
                        }
                    }

                    // キャンセルボタン
                    Row(
                        modifier = Modifier.padding(all = 0.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { viewModel.onAddPictureOtherDialogDismiss() },
                            colors = ButtonDefaults.textButtonColors(
                                containerColor = Color.Transparent,
                                contentColor = ColorHex.hex2Rgb(COLOR_BLUE),
                                disabledContentColor = Color.Transparent
                            )
                        ) {
                            Text(CANCEL)
                        }
                    }
                }
            }
        )
    }
}
