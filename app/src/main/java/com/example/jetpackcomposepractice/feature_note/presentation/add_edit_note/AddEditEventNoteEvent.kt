package com.example.jetpackcomposepractice.feature_note.presentation.add_edit_note

import androidx.compose.ui.focus.FocusState

sealed class AddEditEventNoteEvent {
    data class EnteredTitle (val value :String) : AddEditEventNoteEvent()
    data class ChangeTitleFocus (val focusState: FocusState) : AddEditEventNoteEvent()
    data class EnteredContent (val value :String) : AddEditEventNoteEvent()
    data class ChangeContentFocus (val focusState: FocusState) : AddEditEventNoteEvent()
    data class ChangeColor(val color:Int) : AddEditEventNoteEvent()
    object SaveNote: AddEditEventNoteEvent()
}
