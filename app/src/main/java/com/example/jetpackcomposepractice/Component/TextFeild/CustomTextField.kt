package com.example.jetpackcomposepractice.Component.TextFeild

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposepractice.navigationPassingDataNavHost.ui.theme.JetPackComposePracticeTheme

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    onValueChange: (text: String) -> Unit,
    placeholderText: String = "Placeholder",
    value: String,
    fontSize: TextUnit = MaterialTheme.typography.body2.fontSize,
    fontColor: Color = Color.Black,
    cursorBrush: SolidColor = SolidColor(Color.Blue),
    singleLine: Boolean = false,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    keyboardActions: KeyboardActions = KeyboardActions(),
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    visualTransformation: VisualTransformation = VisualTransformation.None
) {

    BasicTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp),
        value = value,
        onValueChange = onValueChange,
        singleLine = singleLine,
        cursorBrush = cursorBrush,
        textStyle = LocalTextStyle.current.copy(
            color = fontColor,
            fontSize = fontSize
        ),
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (leadingIcon != null) leadingIcon()
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.White)
                ) {
                    if (value.isEmpty()) Text(
                        placeholderText,
                        style = LocalTextStyle.current.copy(
                            color = Color.White,
                            fontSize = fontSize,
                        )
                    )
                    innerTextField()
                }
                if (trailingIcon != null) trailingIcon()
            }
        }
    )
}

@Preview(showBackground = false)
@Composable
fun PreviewCustomTextField() {
    JetPackComposePracticeTheme() {
        CustomTextField(onValueChange = {}, value = "")
    }
}
