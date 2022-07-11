package com.example.jetpackcomposepractice.feature_note.domain.use_case

import com.example.jetpackcomposepractice.feature_note.domain.model.InvalidNoteException
import com.example.jetpackcomposepractice.feature_note.domain.model.Note
import com.example.jetpackcomposepractice.feature_note.domain.repository.NoteRepository
import kotlin.jvm.Throws

class AddNote(
    private val noteRepository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note){
        if(note.title.isBlank()){
            throw InvalidNoteException("The Title of the note can't be empty!!")
        }
        if(note.content.isBlank()){
            throw InvalidNoteException("The Content of the note can't be empty!!")
        }

        noteRepository.insertNote(note)
    }
}