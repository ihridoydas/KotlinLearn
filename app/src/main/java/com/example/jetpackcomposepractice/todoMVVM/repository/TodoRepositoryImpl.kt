package com.example.jetpackcomposepractice.todoMVVM.repository

import com.example.jetpackcomposepractice.todoMVVM.data.dao.TodoDao
import com.example.jetpackcomposepractice.todoMVVM.data.entity.Todo
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(
    private val dao: TodoDao
) : TodoRepository {
    override suspend fun insertTodo(toDo: Todo) {
        dao.insertTodo(toDo)
    }

    override suspend fun deleteTodo(toDo: Todo) {
        dao.deleteTodo(toDo)
    }

    override suspend fun getTodoById(id: Int): Todo? {
        return dao.getTodoById(id)
    }

    override fun getTodos(): Flow<List<Todo>> {
        return dao.getTodos()

    }
}