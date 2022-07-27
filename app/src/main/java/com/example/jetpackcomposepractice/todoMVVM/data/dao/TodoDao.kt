package com.example.jetpackcomposepractice.todoMVVM.data.dao

import androidx.room.*
import com.example.jetpackcomposepractice.todoMVVM.data.entity.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    //Insert TOdo
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(toDo: Todo)

    //Delete Todo
    @Delete
    suspend fun deleteTodo(toDo: Todo)

    //Get data by ID
    @Query("SELECT * FROM toDo WHERE id = :id")
    suspend fun getTodoById(id: Int): Todo?

    //Get all todos
    @Query("SELECT * FROM toDo")
    fun getTodos(): Flow<List<Todo>>
}