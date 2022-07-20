package com.example.jetpackcomposepractice.todo.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.jetpackcomposepractice.todo.data.model.Todo
import com.example.jetpackcomposepractice.todo.ui.viewmodel.TodoViewModel
import com.example.jetpackcomposepractice.ui.theme.JetPackComposePracticeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoActivity : ComponentActivity() {
    private val todoViewModel: TodoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetPackComposePracticeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    //TolBar
                    AddTolbar()

                }
            }
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun AddTolbar() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Todo App")
                    }
                )
            },
            floatingActionButton = {
                val openDialog = remember { mutableStateOf(false) }
                FloatingActionButton(onClick = {
                    openDialog.value = true
                }) {
                    AddDialogBox(openDialog = openDialog)
                    Icon(Icons.Default.Add, contentDescription = "ADD")

                }
            }

        ) {
            RecyclerView(todoViewModel = todoViewModel)
        }

    }

    @Composable
    fun AddDialogBox(openDialog: MutableState<Boolean>) {
        val title = remember { mutableStateOf("") }
        val description = remember { mutableStateOf("") }

        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    openDialog.value = false
                },
                title = {
                    Text(text = "ToDo")
                },
                text = {
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                    ) {
                        //Title
                        OutlinedTextField(
                            value = title.value,
                            onValueChange = {
                                title.value = it
                            },
                            placeholder = {
                                Text(text = "Enter Title")
                            },
                            label = {
                                Text(text = "Enter Title")
                            },
                            modifier = Modifier.padding(10.dp)
                        )

                        //Description
                        OutlinedTextField(
                            value = description.value,
                            onValueChange = {
                                description.value = it
                            },
                            placeholder = {
                                Text(text = "Enter Description")
                            },
                            label = {
                                Text(text = "Enter Description")
                            },
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                },
                confirmButton = {
                    OutlinedButton(onClick = {
                        insert(title, description)
                        openDialog.value = false
                    }) {
                        Text(text = "Save")
                    }
                }
            )
        }
    }

    private fun insert(title: MutableState<String>, description: MutableState<String>) {
        lifecycleScope.launchWhenStarted {

            if (!TextUtils.isEmpty(title.value) && !TextUtils.isEmpty(description.value)) {
                todoViewModel.insert(
                    Todo(
                        title.value,
                        description.value
                    )
                )
                Toast.makeText(this@TodoActivity, "Inserted...", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@TodoActivity, "Empty...", Toast.LENGTH_LONG).show()
            }

        }

    }

    @Composable
    fun EachRow(todo: Todo) {
        Card(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxWidth(),
            elevation = 2.dp,
            shape = RoundedCornerShape(4.dp)
        ) {

            Column(modifier = Modifier.padding(10.dp)) {
                Text(text = todo.title, fontWeight = FontWeight.ExtraBold)
                Text(text = todo.description, fontWeight = FontWeight.Normal, fontStyle = FontStyle.Italic)

            }
        }
    }
    
    @Composable
    fun RecyclerView(todoViewModel: TodoViewModel){
        LazyColumn {
            items(todoViewModel.response.value){todo->
                EachRow(todo = todo)

            }

        }
    }


}
