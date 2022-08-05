package com.example.jetpackcomposepractice.onboardingCompose.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import com.example.jetpackcomposepractice.onboardingCompose.Drawer.MenuItem

val ListOfMenuItem: List<MenuItem> = listOf(
    MenuItem(
        id = "Home",
        title = "Home",
        contentDescription = "Go to home Screen",
        icon = Icons.Default.Home
    ),
    MenuItem(
        id = "Settings",
        title = "Settings",
        contentDescription = "Go to Setting Screen",
        icon = Icons.Default.Settings
    ),
    MenuItem(
        id = "About",
        title = "About",
        contentDescription = "Go to About Screen",
        icon = Icons.Default.Person
    ),
    MenuItem(
        id = "Help",
        title = "Help",
        contentDescription = "Go to Help Screen",
        icon = Icons.Default.Help
    ),

    )
