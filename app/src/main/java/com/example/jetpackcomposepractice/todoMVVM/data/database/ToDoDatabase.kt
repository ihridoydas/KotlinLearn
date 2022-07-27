package com.example.jetpackcomposepractice.todoMVVM.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetpackcomposepractice.todoMVVM.data.dao.TodoDao
import com.example.jetpackcomposepractice.todoMVVM.data.entity.Todo


@Database(
    entities = [Todo::class],
    version = 1,
    exportSchema = false
)
abstract class ToDoDatabase : RoomDatabase() {
    abstract val todoDao: TodoDao
}