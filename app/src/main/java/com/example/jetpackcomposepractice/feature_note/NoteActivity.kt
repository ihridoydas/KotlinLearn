package com.example.jetpackcomposepractice.feature_note

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpackcomposepractice.feature_note.presentation.add_edit_note.AddEditNoteScreen
import com.example.jetpackcomposepractice.feature_note.presentation.add_edit_note.AddEditNoteViewModel
import com.example.jetpackcomposepractice.feature_note.presentation.notes.NotesScreen
import com.example.jetpackcomposepractice.feature_note.presentation.notes.NotesViewModel
import com.example.jetpackcomposepractice.feature_note.presentation.util.Screen
import com.example.jetpackcomposepractice.feature_note.ui.theme.JetPackComposePracticeTheme
import com.example.jetpackcomposepractice.todo.ui.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteActivity : ComponentActivity() {
    private val NotesViewModel: NotesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposePracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                   // modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.NotesScreen.route){
                        composable(
                            route = Screen.NotesScreen.route
                        ){
                           NotesScreen(navController = navController, viewModel = NotesViewModel)
                        }
                        composable(
                            route = Screen.AddEditNoteScreen.route +
                                    "?noteId={noteId}&noteColor={noteColor}",
                            arguments = listOf(
                                navArgument(
                                    name = "noteId",
                                ){
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(
                                    name = "noteColor",
                                ){
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ){
                            val color = it.arguments?.getInt("noteColor") ?: -1
                            AddEditNoteScreen(
                                navConroller = navController,
                                noteColor =color )
                        }
                    }

                }
            }
        }
    }
}

