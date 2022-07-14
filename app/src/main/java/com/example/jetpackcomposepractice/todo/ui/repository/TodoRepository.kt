package com.example.jetpackcomposepractice.todo.ui.repository

import com.example.jetpackcomposepractice.todo.data.model.Todo
import com.example.jetpackcomposepractice.todo.data.dao.TodoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TodoRepository
@Inject
constructor(private val dao: TodoDao) {
    suspend fun insert(todo: Todo) = withContext(Dispatchers.IO) {
        dao.insert(todo)
    }

    fun getAllTodos(): Flow<List<Todo>> = dao.getAllTodos()
}