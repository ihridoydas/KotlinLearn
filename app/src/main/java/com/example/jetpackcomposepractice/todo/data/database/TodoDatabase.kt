package com.example.jetpackcomposepractice.todo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetpackcomposepractice.todo.data.dao.TodoDao
import com.example.jetpackcomposepractice.todo.data.model.Todo

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase(){
    abstract fun getDao(): TodoDao
}