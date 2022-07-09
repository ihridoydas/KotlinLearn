package com.example.jetpackcomposepractice.todo.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

import androidx.room.Query
import com.example.jetpackcomposepractice.todo.data.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todo)

    @Query("SELECT * FROM todoTable")
    fun getAllTodos():Flow<List<Todo>>
}