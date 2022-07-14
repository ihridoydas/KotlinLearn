package com.example.jetpackcomposepractice.feature_note.presentation.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposepractice.feature_note.domain.model.InvalidNoteException
import com.example.jetpackcomposepractice.feature_note.domain.model.Note
import com.example.jetpackcomposepractice.feature_note.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedSateHandle: SavedStateHandle
) : ViewModel() {

    private val _noteTitle = mutableStateOf(
        NoteTextFieldState(
            hint = "Enter title...."
        )
    )
    val noteTitle: State<NoteTextFieldState> = _noteTitle

    private val _noteContent = mutableStateOf(
        NoteTextFieldState(
            hint = "Enter some Content"
        )
    )
    val noteContent: State<NoteTextFieldState> = _noteContent

    private val _noteColor = mutableStateOf(Note.noteColors.random().toArgb())
    val noteColor: State<Int> = _noteColor


    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId: Int? = null

    init {
        savedSateHandle.get<Int>("noteId")?.let { noteId->
            if (noteId != -1){

                viewModelScope.launch {
                    noteUseCases.getNote(noteId)?.also { note->

                        currentNoteId = note.id
                        _noteTitle.value = noteTitle.value.copy(
                           text = note.title,
                            isHintVisible = true
                        )

                        _noteContent.value = noteContent.value.copy(
                            text = note.content,
                            isHintVisible = true
                        )
                        _noteColor.value = note.color

                    }
                }
            }

        }
    }

    fun onEvent(event: AddEditEventNoteEvent) {
        when (event) {
            is AddEditEventNoteEvent.EnteredTitle -> {
                _noteTitle.value = noteTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditEventNoteEvent.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteTitle.value.text.isBlank()
                )
            }

            is AddEditEventNoteEvent.EnteredContent -> {
                _noteContent.value = _noteContent.value.copy(
                    text = event.value
                )
            }
            is AddEditEventNoteEvent.ChangeContentFocus -> {
                _noteContent.value = _noteContent.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteContent.value.text.isBlank()
                )
            }

            is AddEditEventNoteEvent.ChangeColor -> {
                _noteColor.value = event.color
            }
            is AddEditEventNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        noteUseCases.addNote(
                            Note(
                                title = noteTitle.value.text,
                                content = noteContent.value.text,
                                timestamp = System.currentTimeMillis(),
                                color = noteColor.value,
                                id = currentNoteId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch (e: InvalidNoteException) {
                        _eventFlow.emit(
                            UiEvent.showSnackbar(
                                message = e.message ?: "Could`t save note!!"
                            )
                        )

                    }
                }
            }


        }

    }

    sealed class UiEvent {
        data class showSnackbar(val message: String) : UiEvent()
        object SaveNote : UiEvent()
    }


}