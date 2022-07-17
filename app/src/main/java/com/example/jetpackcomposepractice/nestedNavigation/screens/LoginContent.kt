package com.example.jetpackcomposepractice.nestedNavigation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun LoginContent(
    onClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onForgotClick: () -> Unit,
    onPracticeClick: () -> Unit,
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.clickable { onClick() },
            text = "LOGIN",
            fontSize = MaterialTheme.typography.h3.fontSize,
            fontWeight = FontWeight.Medium

        )
        Text(
            modifier = Modifier.clickable { onSignUpClick() },
            text = "SignUP",
            fontSize = MaterialTheme.typography.h3.fontSize,
            fontWeight = FontWeight.Medium

        )
        Text(
            modifier = Modifier.clickable { onForgotClick() },
            text = "Forgot",
            fontSize = MaterialTheme.typography.h3.fontSize,
            fontWeight = FontWeight.Medium

        )


        Column(
            modifier = Modifier.padding(top = 220.dp)
        ) {
            Text(
                modifier = Modifier.clickable { onPracticeClick() },
                text = "Practice",
                fontSize = MaterialTheme.typography.h3.fontSize,
                fontWeight = FontWeight.Medium,

                )

        }

    }



}