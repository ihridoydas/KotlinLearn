package com.example.jetpackcomposepractice.learnConstraintLayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Preview(showBackground = true)
@Composable
fun ConstraintLayoutSample(
) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (icon, title, bar) = createRefs() // (1)
        Icon(
            modifier = Modifier
                .padding(4.dp)
                .constrainAs(icon) { // (2)
                    top.linkTo(title.top) // (3)
                    bottom.linkTo(title.bottom)
                    start.linkTo(parent.start) // (4)
                },
            imageVector = Icons.Rounded.Info,
            contentDescription = "Info"
        )
        Text(
            modifier = Modifier.constrainAs(title) {
                start.linkTo(icon.end)
            },
            text = "タイトル",
            fontSize = 32.sp
        )
        Spacer(
            modifier = Modifier
                .constrainAs(bar) {
                    top.linkTo(title.bottom)
                    start.linkTo(title.start)
                    end.linkTo(title.end)
                    width = Dimension.fillToConstraints // (5)
                    height = Dimension.percent(0.1f) // (6)
                }
                .background(color = Color.DarkGray)
        )
    }
}


