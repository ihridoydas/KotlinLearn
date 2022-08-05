package com.example.jetpackcomposepractice.onboardingCompose.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposepractice.onboardingCompose.Drawer.DrawerBody
import com.example.jetpackcomposepractice.onboardingCompose.Drawer.DrawerHeader
import com.example.jetpackcomposepractice.onboardingCompose.Drawer.TopAppBarCompose
import kotlinx.coroutines.launch


@Composable
fun HomeScreen() {
    TopBar()
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TopBar() {
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBarCompose(
                onNavigationIconClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }

                }
            )

        },
        //when no need is swapping Gesture
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            DrawerHeader(scaffoldState)
            DrawerBody(
                items = ListOfMenuItem,
                modifier = Modifier,
                onItemClick = {
                    //println("Click on ${it.title}")
                    Toast.makeText(context, it.title, Toast.LENGTH_LONG).show()
                }
            )
        },

        content = { ContentCompose() }
    )
}


@Composable
fun ContentCompose() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
    )

}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen()
}