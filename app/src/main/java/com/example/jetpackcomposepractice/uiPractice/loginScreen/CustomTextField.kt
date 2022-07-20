package com.example.jetpackcomposepractice.uiPractice.loginScreen

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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.example.jetpackcomposepractice.albumFeature.util.PLACE_HOLDER_GRAY

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (text: String) -> Unit,
    modifier: Modifier = Modifier,
    singleLine: Boolean = false,
    colorBrush: SolidColor = SolidColor(Color("#0068B7".toColorInt())),
    fontColor: Color = Color.Black,
    fontSize: TextUnit = MaterialTheme.typography.body2.fontSize,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leadingIcon: (@Composable () -> Unit)? = null,
    placeholderText: String = "PlaceHolder",
    trailingIcon : (@Composable () -> Unit)? = null,



) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth()
            .padding(5.dp),
        singleLine = singleLine,
        cursorBrush = colorBrush,
        textStyle = LocalTextStyle.current.copy(
            color = fontColor,
            fontSize = fontSize,
        ),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = visualTransformation,
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) leadingIcon()
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.White)
                ) {
                    if (value.isEmpty()) Text(
                        placeholderText, style = LocalTextStyle.current.copy(
                            color = Color(PLACE_HOLDER_GRAY.toColorInt()),
                            fontSize = fontSize
                        )
                    )
                    innerTextField()

                }
                if(trailingIcon != null) trailingIcon()

            }
        }


    )


}


@Preview(showBackground = true)
@Composable
fun PreviewCustomTextField() {

    CustomTextField(onValueChange = {}, value = "")
}
