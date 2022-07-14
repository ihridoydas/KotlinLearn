package com.example.jetpackcomposepractice.viewPager

import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposepractice.MainActivity
import com.example.jetpackcomposepractice.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen() {
    val pageState = rememberPagerState()
    val context = LocalContext.current

    Column {
        HorizontalPager(
            count = datalist.size,
            state = pageState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)

        ) { page ->
            PageUI(pager = datalist[page])

        }
        HorizontalPagerIndicator(
            pagerState = pageState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(20.dp),
            activeColor = colorResource(id = R.color.purple_500)
        )

        AnimatedVisibility(visible = pageState.currentPage == 2) {
            Button(onClick = {
                val navigate = Intent(context,MainActivity::class.java)
                context.startActivity(navigate)

            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Get Started..")
            }

        }
    }
}

@Composable
fun PageUI(pager: Pager) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = pager.image),
            contentDescription = pager.des,
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = pager.des)

    }
}