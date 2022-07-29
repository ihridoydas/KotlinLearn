package com.example.jetpackcomposepractice.topBarWithNavGraph

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposepractice.topBarWithNavGraph.ui.theme.JetPackComposePracticeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopBarNavGraphActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposePracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface {
                    TopBarScreen()

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarScreen() {
    var currentToolbarState by remember { mutableStateOf(TopBarViewState()) }

    Scaffold(
        topBar = {
            AnimatedTopBar(topBarViewState = currentToolbarState)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Button(onClick = {
                currentToolbarState = TopBarViewState(
                    title = "Top Bar",
                )
            }) {
                Text("Title toolbar")
            }
            Spacer(modifier = Modifier.padding(16.dp))
            Button(onClick = {
                currentToolbarState = TopBarViewState(
                    title = "Top Bar title",
                    subTitle = "Top Bar Sub Title",
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Hello description",
                            tint = Color.White,
                        )
                    }
                )
            }) {
                Text("Subtitle toolbar")
            }
            Spacer(modifier = Modifier.padding(16.dp))
            Button(onClick = {
                currentToolbarState = TopBarViewState(
                    isVisible = false,
                )
            }) {
                Text("No toolbar")
            }
        }
    }
}

@Composable
fun AnimatedTopBar(topBarViewState: TopBarViewState) {
    val topBarSpring: FiniteAnimationSpec<IntSize> = spring(
        dampingRatio = Spring.DampingRatioLowBouncy,
        stiffness = Spring.StiffnessVeryLow,
    )

    AnimatedVisibility(
        visible = topBarViewState.isVisible,
        enter = expandVertically(animationSpec = topBarSpring),
        exit = shrinkVertically(animationSpec = topBarSpring),
    ) {
        TopAppBar {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.CenterStart,
            ) {
                Column(
                    modifier = Modifier
                        .animateContentSize(animationSpec = topBarSpring)
                        .padding(8.dp)
                ) {
                    topBarViewState.title?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.body1
                        )
                    }
                    topBarViewState.subTitle?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.body2
                        )
                    }
                }

                androidx.compose.animation.AnimatedVisibility(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    visible = topBarViewState.icon != null,
                    enter = fadeIn() + slideInHorizontally(
                        initialOffsetX = { it / 2 },
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessVeryLow,
                            visibilityThreshold = IntOffset.VisibilityThreshold,
                        ),
                    ),
                    exit = fadeOut(),
                ) {
                    IconButton(
                        onClick = { topBarViewState.onActionClick?.invoke() }
                    ) {
                        topBarViewState.icon?.invoke()
                    }
                }
            }
        }
    }
}