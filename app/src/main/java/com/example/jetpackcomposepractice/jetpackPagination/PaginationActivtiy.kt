package com.example.jetpackcomposepractice.jetpackPagination

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackcomposepractice.jetpackPagination.ui.theme.JetPackComposePracticeTheme

class PaginationActivtiy : ComponentActivity() {

    private lateinit var viewModel: PagingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)
            .get(PagingViewModel::class.java)

        viewModel.getAnimals()
        setContent {
            JetPackComposePracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(title = { Text("Animals") })
                        }
                    ) {
                        AnimalList(liveAnimals = viewModel.liveAnimals)
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun AnimalList(liveAnimals: LiveData<List<Animal>>) {
        val animals by liveAnimals.observeAsState(initial = emptyList())

        LazyColumn {
            itemsIndexed(items = animals) { index, animal ->

                if (index == animals.lastIndex) {
                    viewModel.getAnimals()
                }
                ListItem(
                    icon = { Text(animal.emoji) },
                    text = { Text(animal.name) },
                    modifier = Modifier.padding(vertical = 30.dp)
                )

            }

        }

    }
}


@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    JetPackComposePracticeTheme {
        Greeting2("Android")
    }
}