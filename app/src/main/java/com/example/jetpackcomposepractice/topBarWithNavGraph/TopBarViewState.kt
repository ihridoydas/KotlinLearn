package com.example.jetpackcomposepractice.topBarWithNavGraph

import androidx.compose.runtime.Composable

data class TopBarViewState(
    val title: String? = null,
    val subTitle: String? = null,
    val icon: (@Composable () -> Unit)? = null,
    val onActionClick: (() -> Unit)? = null,
    val isVisible: Boolean = true,
)
