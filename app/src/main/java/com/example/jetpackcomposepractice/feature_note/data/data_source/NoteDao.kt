package com.example.jetpackcomposepractice.feature_note.data.data_source

import androidx.room.*
import com.example.jetpackcomposepractice.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {

    @Query("SELECT * FROM noteTable")
    fun getNotes(): Flow<List<Note>>


    @Query("SELECT * FROM noteTable WHERE id = :id")
    suspend fun getNoteById(id:Int):Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note:Note)

    @Delete
    suspend fun deleteNote(note: Note)

}