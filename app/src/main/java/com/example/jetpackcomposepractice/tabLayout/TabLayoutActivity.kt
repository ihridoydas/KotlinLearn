package com.example.jetpackcomposepractice.tabLayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuOpen
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.TabRowDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposepractice.tabLayout.ui.theme.JetPackComposePracticeTheme
import com.example.jetpackcomposepractice.tabLayout.ui.theme.TabColorOne
import com.example.jetpackcomposepractice.tabLayout.view.TabScreenOne
import com.example.jetpackcomposepractice.tabLayout.view.TabScreenThree
import com.example.jetpackcomposepractice.tabLayout.view.TabScreenTwo
import com.google.accompanist.pager.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TabLayoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposePracticeTheme {
                // A surface container using the 'background' color from the theme
                Column {
                    Toolbar()
                    TabScreen()
                }
            }
        }
    }
}

@Composable
fun Toolbar() {
    TopAppBar(
        title = { Text(text = "Tab Layout", color = Color.White) },
        backgroundColor = TabColorOne,
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.MenuOpen,
                contentDescription = null,
                tint = Color.White
            )
        },


        )
}

//List of Screen
val list = listOf("Home", "About", "Portfolio")
val lists = listOf(
    Icons.Filled.Home,
    Icons.Filled.Person,
    Icons.Filled.Work,
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(pagerState: PagerState) {
    HorizontalPager(state = pagerState, count = list.size) { page ->
        when (page) {
            0 -> TabScreenOne(tabName = "This is a Home Layout")
            1 -> TabScreenTwo(tabName = "This is a About Layout")
            2 -> TabScreenThree(tabName = "This is a Portfolio Layout")

        }

    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabScreen() {
    val pagerState = rememberPagerState(0)
    Column(modifier = Modifier.background(Color.White)) {
        Tabs(pagerState = pagerState)
        TabsContent(pagerState = pagerState)
    }

}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(pagerState: PagerState) {

    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = TabColorOne,
        contentColor = Color.White,

        divider = {
            TabRowDefaults.Indicator(
                //thickness = 3.dp,
                color = Color.White
            )
        },
        indicator = { tabpositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabpositions),
                height = 3.dp,
                color = Color.Red
            )
        }
    ) {
        list.forEachIndexed { index, _ ->
            Tab(
                text = {
                    Text(
                        text = list[index],
                        color = if (pagerState.currentPage == index) Color.White else Color.LightGray
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                icon = {
                    Icon(imageVector = lists[index], contentDescription = null)
                }
            )

        }


    }

}


@Preview(showBackground = true)
@Composable
fun TabLayoutActivityPreview() {
    JetPackComposePracticeTheme {
        Toolbar()
        TabScreen()

    }
}