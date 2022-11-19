package com.example.jetpackcomposepractice.retrofitAPI

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetpackcomposepractice.retrofitAPI.model.Post
import com.example.jetpackcomposepractice.retrofitAPI.ui.theme.JetPackComposePracticeTheme
import com.example.jetpackcomposepractice.retrofitAPI.ui.viewModel.DataViewModel
import com.example.jetpackcomposepractice.retrofitAPI.utils.ApiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RetrofitActivity : ComponentActivity() {
    //private val dataViewModel: DataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposePracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val dataViewModel: DataViewModel = hiltViewModel()
                    GetData(dataViewModel = dataViewModel)

                }
            }
        }
    }

    @Composable
    fun EachRow(post:Post) {
        Card(modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
            elevation = 2.dp,
            shape = RoundedCornerShape(4.dp)
        ) {
            Column {
                Text(text = "Title: ${post.title}",modifier = Modifier.padding(8.dp), fontStyle = FontStyle.Normal)
                Text(text = post.body, modifier = Modifier.padding(10.dp), fontStyle = FontStyle.Italic)

            }
        }
    }

    @Composable
    fun GetData(dataViewModel: DataViewModel){

        when (val result = dataViewModel.response.value){
            is ApiState.Success -> {
                LazyColumn {
                    items(result.data) { reponse ->
                        EachRow(reponse)
                    }
                }
            }
            is ApiState.Failure -> {
                Text(text = "${result.msg}")
            }
            is ApiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is ApiState.Empty -> {}
        }

    }
}


