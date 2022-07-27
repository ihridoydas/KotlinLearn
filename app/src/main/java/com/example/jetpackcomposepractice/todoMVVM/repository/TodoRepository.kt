package com.example.jetpackcomposepractice.todoMVVM.repository

import com.example.jetpackcomposepractice.todoMVVM.data.entity.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    //Insert TOdo
    suspend fun insertTodo(toDo: Todo)

    //Delete Todo
    suspend fun deleteTodo(toDo: Todo)

    //Get data by ID
    suspend fun getTodoById(id: Int): Todo?

    //Get all todos
    fun getTodos(): Flow<List<Todo>>
}