/*
 * Created by hridoydas on 2022/12/23
 * Last modified 12/23/22, 11:11 PM
 * Copyright © 2022 Cognivision Inc. All rights reserved.
 */

package com.example.jetpackcomposepractice.Component.TextFeild

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun NumberPlateBasicCustomTextField(
    value: String = "",
    onTextChanged: ((String) -> Unit) = {},
    singleLine: Boolean = false,
    cursorBrush: SolidColor = SolidColor(Color.Black),
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    textStyle : TextStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
    placeholder: String = "",
    onFocusChanged: ((FocusState) -> Unit) = {},
    modifier: Modifier,
    fontSize: TextUnit,
) {
    BasicTextField(
        value = value,
        onValueChange = {
            onTextChanged(it)
        },
        singleLine = singleLine,
        cursorBrush = cursorBrush,
        keyboardOptions = keyboardOptions,
        textStyle = textStyle,
        modifier = modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .onFocusChanged {
                onFocusChanged(it)
            },

        decorationBox = { innerTextField ->
            Box(
                modifier = modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(10.dp), // inner padding
            ) {
                if (value.isEmpty()) {
                    Text(
                        placeholder,//Placeholder
                        fontSize  = fontSize,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        modifier = modifier.fillMaxWidth(),
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    innerTextField()
                }
            }
        }
    )
}

@Preview
@Composable
fun CustomFieldPreview() {
    NumberPlateBasicCustomTextField(modifier = Modifier, fontSize = MaterialTheme.typography.body2.fontSize)
}

