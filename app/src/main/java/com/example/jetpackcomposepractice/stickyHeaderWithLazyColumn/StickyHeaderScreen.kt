package com.example.jetpackcomposepractice.stickyHeaderWithLazyColumn

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposepractice.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StickyHeaderScreen() {


    val listItems: List<ListItem> = listOf(
        ListItem("Jayme", data = "2022/11/11"),
        ListItem("Jayme", data = "2022/11/11"),
        ListItem("Jayme", data = "2022/11/11"),
        ListItem("Jayme", data = "2022/11/11"),
        ListItem("Jayme", data = "2022/11/11"),
        ListItem("Jayme", data = "2022/11/11"),
        ListItem("Jayme", data = "2022/11/11"),
        ListItem("Jayme", data = "2022/11/11"),
        ListItem("Jayme", data = "2022/11/11"),
        ListItem("Jayme", data = "2022/11/11"),
        ListItem("Gil", data = "2022/11/12"),
        ListItem("Gil", data = "2022/11/12"),
        ListItem("Gil", data = "2022/11/12"),
        ListItem("Juice WRLD", data = "2022/11/12"),
        ListItem("Callan", data = "2022/11/12"),
        ListItem("Braxton", data = "2022/11/11"),
        ListItem("Kyla", data = "2022/11/12"),
        ListItem("Lil Mosey", data = "2022/11/12"),
        ListItem("Allan", data = "2022/11/11"),
        ListItem("Mike", data = "2022/11/12"),
        ListItem("Drew", data = "2022/11/12"),
        ListItem("Nia", data = "2022/11/11"),
        ListItem("Coi Relay", data = "2022/11/12")
    )

    DisplayList(items = listItems)


}

//List Item of sticky Header

@Composable
fun ListItem(item: ListItem) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .height(60.dp)
            .background(color = Color.Gray)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.badge2),
                contentDescription = "user icon",
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterVertically),
                text = item.name,
                color = Color.White,
                fontSize = 16.sp
            )
        }
        /*  Row(
              modifier = Modifier
                  .align(CenterEnd)
          ) {
              Image(
                  painter = painterResource(id = R.drawable.ic_mail),
                  contentDescription = "mail button",
                  modifier = Modifier
                      .align(CenterVertically)
                      .padding(8.dp)
              )
          }*/
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalFoundationApi
@Composable
fun DisplayList(items: List<ListItem>) {
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    LazyColumn(modifier = Modifier.fillMaxSize(1F), state = listState) {
        val grouped = items.groupBy { it.data }
        grouped.forEach { (initial, items) ->
            stickyHeader {
                Text(
                    text = initial.toString(),
                    modifier = Modifier.padding(10.dp)
                )
            }
            items(items) { item ->
                ListItem(item = item)
            }
        }
        scope.launch {
            listState.scrollToItem(items.size - 1)
        }

//        CoroutineScope(Dispatchers.IO).launch {
//
//        }
    }
}


@Composable
fun StickyHeaderPreview() {


}