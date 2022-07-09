package com.example.jetpackcomposepractice.TODO.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todoTable")
data class Todo(
    val title: String,
    val description: String
){
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null
}
