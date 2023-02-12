package com.example.jetpackcomposepractice.handling_events_with_sealed_classes.event

sealed class UIEvent {
    data class showMessage(val message:String) : UIEvent()
}