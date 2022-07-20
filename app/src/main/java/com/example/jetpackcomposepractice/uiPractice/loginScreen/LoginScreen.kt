package com.example.jetpackcomposepractice.uiPractice.loginScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.jetpackcomposepractice.R
import com.example.jetpackcomposepractice.albumFeature.util.COLOR_BLUE
import com.example.jetpackcomposepractice.albumFeature.util.COLOR_GRAY1
import com.example.jetpackcomposepractice.albumFeature.util.COLOR_GRAY2
import com.example.jetpackcomposepractice.albumFeature.util.TEXT_FIELD_BORDER_GRAY

@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(COLOR_GRAY2.toColorInt())),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginTopbar(painterResource(R.drawable.ic_logo))
        Spacer(modifier = Modifier.weight(0.5f))
        LoginInputField()
        Spacer(modifier = Modifier.weight(2f))
        LoginScreenBottom()


    }

}

@Composable
fun LoginTopbar(
    image: Painter
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = image,
            contentDescription = "Logo",
            contentScale = ContentScale.Fit
        )
    }

}

@Composable
fun LoginInputField() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 40.dp, end = 40.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_login_icon_lock),
                contentDescription = "Lock Icon"
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)

        ) {
            var mailValue by remember { mutableStateOf("") }
            var isMailAddressInputFieldEmpty by remember { mutableStateOf(true) }



            CustomTextField(
                value = mailValue,
                onValueChange = {
                    mailValue = it
                    isMailAddressInputFieldEmpty = it.isEmpty()
                },
                placeholderText = "メールアドレスを入力",
                singleLine = true,
                modifier = Modifier
                    .height(44.dp)
                    .border(
                        width = 1.dp,
                        color = Color(TEXT_FIELD_BORDER_GRAY.toColorInt()),
                        shape = RoundedCornerShape(3)
                    ),
                fontSize = 15.sp
            )


        }


        Spacer(modifier = Modifier.height(30.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)

        ) {
            var passValue by remember { mutableStateOf("") }
            var passwordVisibilty by remember { mutableStateOf(false) }
            var isPasswordInputFieldEmpty by remember { mutableStateOf(false) }
            if (passValue != "") isPasswordInputFieldEmpty = false


            val icon = if (passwordVisibilty)
                painterResource(id = R.drawable.ic_visibility_on)
            else
                painterResource(id = R.drawable.ic_visibility_off)

            CustomTextField(
                value = passValue,
                onValueChange = {
                    passValue = it
                    isPasswordInputFieldEmpty = it.isEmpty()
                },
                placeholderText = "パスワード入力",
                singleLine = true,
                modifier = Modifier
                    .height(44.dp)
                    .border(
                        width = 1.dp,
                        color = Color(TEXT_FIELD_BORDER_GRAY.toColorInt()),
                        shape = RoundedCornerShape(3)
                    ),
                fontSize = 15.sp,
                trailingIcon = {
                    IconButton(onClick = {
                        passwordVisibilty = !passwordVisibilty
                    }) {
                        Icon(
                            painter = icon, contentDescription = "visibility",
                            tint = Color.Unspecified,
                            modifier = Modifier.padding(4.dp, 5.dp)
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                visualTransformation = if (passwordVisibilty) VisualTransformation.None else PasswordVisualTransformation()
            )
        }
        Spacer(modifier = Modifier.height(30.dp))

        Surface(
            modifier = Modifier
                .background(color = Color("#D8E0E6".toColorInt()))
                .height(44.dp)
        ) {
            Button(
                onClick = {

                }, modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 4.dp,
                    bottomEnd = 4.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color("#0068B7".toColorInt()),
                    contentColor = Color.White,
                    disabledBackgroundColor = Color("#D8E0E6".toColorInt()),
                    disabledContentColor = Color("#999999".toColorInt())
                )
            ) {
                Text(
                    text = "ログイン",
                    fontWeight = FontWeight.Bold
                )

            }

        }

    }
}

@Composable
fun LoginScreenBottom(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedButton(
            onClick = { /*TODO*/ },
            modifier = modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(start = 40.dp, end = 40.dp),
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(1.dp, Color(COLOR_BLUE.toColorInt())),
            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.White)
        ) {
            Text(text = "生体認証でログイン",
                color =  Color("#0068B7".toColorInt())
            )

        }

    }

}
