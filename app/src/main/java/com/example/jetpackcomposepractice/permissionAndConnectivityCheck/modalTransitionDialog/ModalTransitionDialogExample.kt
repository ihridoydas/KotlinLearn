package com.example.jetpackcomposepractice.permissionAndConnectivityCheck.modalTransitionDialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

@Composable
fun BottmSheetDialog() {
    var showModalTransitionDialog by remember { mutableStateOf(false) }

    MainContent(
        onDismissRequest = { showModalTransitionDialog = false },
        showModalTransitionDialog = showModalTransitionDialog,
        onShowDialog = { showModalTransitionDialog = true }
    )
}

@Composable
private fun MainContent(
    onShowDialog: () -> Unit,
    onDismissRequest: () -> Unit,
    showModalTransitionDialog: Boolean
) {

    Box() {
        Button(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .align(Alignment.Center),
            onClick = onShowDialog
        ) {
            Text(text = "Show dialog")
        }
    }

    if (showModalTransitionDialog) {
        SampleModalTransitionDialog(onDismissRequest)
    }
}

@Composable
private fun SampleModalTransitionDialog(
    onDismissRequest: () -> Unit
) {
    ModalTransitionDialog(onDismissRequest = onDismissRequest) { modalTransitionDialogHelper ->
        Column(
            modifier = Modifier
                .layout { measurable, constraints ->
                    val placeable = measurable.measure(constraints)
                    layout(placeable.width, placeable.height) {
                        //  placeable.placeRelative(x.roundToPx(), y.roundToPx())
                        placeable.place(
                            IntOffset(0, 100)
                        )
                    }
                }
//                .offset(0.dp, (-10).dp)
                .size(400.dp, 450.dp)
                .background(Color.White),
        ) {

            Button(
                modifier = Modifier.align(Alignment.End),
                onClick = modalTransitionDialogHelper::triggerAnimatedClose
            ) {}

            Spacer(modifier = Modifier.size(20.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Modal Transition Dialog"
            )

            Box() {
                Button(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
                    onClick = modalTransitionDialogHelper::triggerAnimatedClose
                ) {
                    Text(text = "Close it", color = Color.White)
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun DialogPreview() {
//    SampleModalTransitionDialog(onDismissRequest = {})
//
//}