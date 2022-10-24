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
import androidx.compose.material.icons.twotone.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposepractice.casecadeMenuAndButtonEffect.casecade.CascadeMenu
import com.example.jetpackcomposepractice.casecadeMenuAndButtonEffect.casecade.CascadeMenuItem
import com.example.jetpackcomposepractice.casecadeMenuAndButtonEffect.casecade.cascadeMenu
import com.example.jetpackcomposepractice.topBarWithNavGraph.ui.theme.JetPackComposePracticeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopBarNavGraphActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposePracticeTheme {
                val context = LocalContext.current
                // A surface container using the 'background' color from the theme
                Surface {
                    TopBarScreen()

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun TopBarScreen() {
    var currentToolbarState by remember { mutableStateOf(TopBarViewState()) }

    //CaseCade Menu Start
    val snackbarHostState = remember { SnackbarHostState() }
    val channel = remember { Channel<String>(Channel.CONFLATED) }
    val (isOpen, setIsOpen) = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = channel) {
        channel.receiveAsFlow().collect {
            snackbarHostState.showSnackbar(it)
        }
    }

    val scope = rememberCoroutineScope()
    //CaseCade Menu End

    Scaffold(
        topBar = {
            AnimatedTopBar(topBarViewState = currentToolbarState)
        },
        modifier = Modifier.fillMaxSize(),
        backgroundColor = Color.Transparent,
        scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState),

        ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            Button(onClick = {
                currentToolbarState = TopBarViewState(
                    title = "Top Bar",
                )
            }) {
                Text("Title toolbar")
            }
            Spacer(modifier = Modifier.padding(16.dp))

            Menu(
                isOpen = isOpen,
                setIsOpen = setIsOpen,
                itemSelected = {
//                     channel.trySend(it)
                    setIsOpen(false)

                    if (it == "about") {
                        scope.launch {
                            snackbarHostState.showSnackbar("Hello I am about ", "HEllo")
                        }
                    } else if (it == "copy") {
                        scope.launch {
                            snackbarHostState.showSnackbar("Hello I am Copy ", "Copy")
                        }
                    }

                })

            Button(onClick = {
                setIsOpen(false)
                currentToolbarState = TopBarViewState(
                    title = "Top Bar title",
                    subTitle = "Top Bar Sub Title",
                    icon = {
                        //Use Another One
                        // CascadeScreen()
                        IconButton(
                            onClick = { setIsOpen(true) }) {
                            Icon(
                                Icons.Filled.Add,
//                                    imageVector = Icons.TwoTone.MoreVert,
                                contentDescription = null
                            )
                        }

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
            Spacer(modifier = Modifier.padding(16.dp))
            //CaseCade Munu
            //CascadeScreen()
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


@ExperimentalAnimationApi
@Composable
fun Menu(isOpen: Boolean = false, setIsOpen: (Boolean) -> Unit, itemSelected: (String) -> Unit) {
    val menu = getMenu()
    CascadeMenu(
        isOpen = isOpen,
        menu = menu,
        onItemSelected = itemSelected,
        onDismiss = { setIsOpen(false) },
        offset = DpOffset(8.dp, 0.dp),
    )
}

fun getMenu(): CascadeMenuItem<String> {
    val menu = cascadeMenu<String> {
        item("about", "About") {
            icon(Icons.TwoTone.Language)
        }
        item("copy", "Copy") {
            icon(Icons.TwoTone.FileCopy)
        }
        item("share", "Share") {
            icon(Icons.TwoTone.Share)
            item("to_clipboard", "To clipboard") {
                item("pdf", "PDF")
                item("epub", "EPUB")
                item("web_page", "Web page")
                item("microsoft_word", "Microsoft word")
            }
            item("as_a_file", "As a file") {
                item("pdf", "PDF")
                item("epub", "EPUB")
                item("web_page", "Web page")
                item("microsoft_word", "Microsoft word")
            }
        }
        item("remove", "Remove") {
            icon(Icons.TwoTone.DeleteSweep)
            item("yep", "Yep") {
                icon(Icons.TwoTone.Done)
            }
            item("go_back", "Go back") {
                icon(Icons.TwoTone.Close)
            }
        }
    }
    return menu
}