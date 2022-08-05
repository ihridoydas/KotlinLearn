package com.example.jetpackcomposepractice.onboardingCompose.Drawer

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposepractice.R

@Composable
fun TopAppBarCompose(
    onNavigationIconClick: () -> Unit

) {
    val context = LocalContext.current
    TopAppBar(
        title = {
            Image(
                painterResource(id = R.drawable.myname),
                contentDescription = "logo",
                modifier = Modifier
                    .height(35.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "Home Screen",
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(
                    Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = Color.White
                )
            }

        },
        actions = {
            IconButton(onClick = {
                Toast.makeText(context, "Star", Toast.LENGTH_LONG).show()
            }) {
                Icon(
                    Icons.Default.Star,
                    contentDescription = "Star",
                    tint = Color.White
                )

            }
            IconButton(onClick = {
                Toast.makeText(context, "Search", Toast.LENGTH_LONG).show()
            }) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.White
                )

            }
            IconButton(onClick = {
                Toast.makeText(context, "Thump UP", Toast.LENGTH_LONG).show()
            }) {
                Icon(
                    Icons.Default.ThumbUp,
                    contentDescription = "Thump UP",
                    tint = Color.White
                )

            }

        },
        backgroundColor = Color.Black,
        contentColor = Color.White
    )

}