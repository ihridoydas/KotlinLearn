package com.example.jetpackcomposepractice.permissionAndConnectivityCheck

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.jetpackcomposepractice.ui.theme.bottomSheetColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet() {
    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    val scope = rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        backgroundColor = Color.Blue,
        sheetGesturesEnabled = false,
        drawerGesturesEnabled = false,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Text(
                        text = "Bottom sheet",
                        fontSize = 10.sp
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(
                        modifier = Modifier.clickable {
                            scope.launch {
                                sheetState.collapse()
                            }
                        },
                        text = "Close",
                        fontSize = 20.sp
                    )
                }

            }
        },
        sheetBackgroundColor = bottomSheetColor,
        sheetPeekHeight = 0.dp
    ) {

        val lifecycleOwner = LocalLifecycleOwner.current

        DisposableEffect(
            key1 = lifecycleOwner,
            effect = {
                val observer = LifecycleEventObserver { _, event ->
                    if (event == Lifecycle.Event.ON_START) {
                        scope.launch {
                            if (sheetState.isCollapsed) {
                                sheetState.expand()
                            } else {
                                sheetState.collapse()
                            }
                        }
                    }

                }
                lifecycleOwner.lifecycle.addObserver(observer)
                onDispose {
                    lifecycleOwner.lifecycle.removeObserver(observer)

                }

            }
        )


//        Box(
//            modifier = Modifier
//                .fillMaxSize(),
//            contentAlignment = Alignment.Center
//        ) {
//            Button(onClick = {
//                scope.launch {
//                    if (sheetState.isCollapsed) {
//                        sheetState.expand()
//                    } else {
//                        sheetState.collapse()
//                    }
//                }
//            }) {
//                Text(text = "Bottom sheet fraction")
//            }
//        }
    }

}