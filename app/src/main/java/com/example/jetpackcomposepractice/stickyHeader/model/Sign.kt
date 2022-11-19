package com.example.jetpackcomposepractice.stickyHeader.model


data class SignGroup(val title: String, val signs: List<Sign>)

data class Sign(
    val name: String,
    val title: String,
    val description: String? = null,
    val imageName: String
)

