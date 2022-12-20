/*
 * Created by hridoydas on 2022/12/06
 * Last modified 12/6/22, 5:41 PM
 * Copyright © 2022 Cognivision Inc. All rights reserved.
 */

package com.example.jetpackcomposepractice.ResponsiveText

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp


data class WindowSize(
    val width: WindowType,
    val height: WindowType
)

enum class WindowType { Compact, Small }

/**
 * ResponsiveTextのTEXT_SCALE_REDUCTION_INTERVAL
 */
private const val TEXT_SCALE_REDUCTION_INTERVAL = 0.9f

@Composable
fun rememberWindowSize(): WindowSize {
    val configuration = LocalConfiguration.current
    val screenWidth by remember(key1 = configuration) {
        mutableStateOf(configuration.screenWidthDp)
    }
    val screenHeight by remember(key1 = configuration) {
        mutableStateOf(configuration.screenHeightDp)
    }

    return WindowSize(
        width = when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                getScreenWidthLand(screenWidth)
            }
            Configuration.ORIENTATION_PORTRAIT -> {
                getScreenWidth(screenWidth)
            }
            else -> {
                getScreenWidth(screenWidth)
            }
        },
        height = when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                getScreenHeightLand(screenHeight)
            }
            Configuration.ORIENTATION_PORTRAIT -> {
                getScreenHeight(screenHeight)
            }
            else -> {
                getScreenHeight(screenHeight)
            }
        }
    )
}

// ORIENTATION PORTRAIT のため
fun getScreenWidth(width: Int): WindowType = when {
    width > 338 -> WindowType.Compact
    else -> WindowType.Small

}

fun getScreenHeight(height: Int): WindowType = when {
    height > 774 -> WindowType.Compact
    else -> WindowType.Small
}

// ORIENTATION LANDSCAPE のため
fun getScreenWidthLand(width: Int): WindowType = when {
    width >= 716 -> WindowType.Compact
    else -> WindowType.Small

}

fun getScreenHeightLand(height: Int): WindowType = when {
    height >= 320 -> WindowType.Compact
    else -> WindowType.Small
}

/**
 * Responsive Custom Text
 */
@Composable
fun ResponsiveText(
    modifier: Modifier = Modifier,
    text: AnnotatedString,
    color: Color,
    textAlign: TextAlign,
    textStyle: TextStyle,
    targetTextSize: TextUnit = textStyle.fontSize,
    maxLines: Int = Int.MAX_VALUE,
    softWrap: Boolean = true,
    overflow: TextOverflow = TextOverflow.Clip,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    onClick: (Int) -> Unit = {}
) {
    var textSize: TextUnit by remember { mutableStateOf(targetTextSize) }

    val layoutResult = remember { mutableStateOf<TextLayoutResult?>(null) }
    val pressIndicator = Modifier.pointerInput(onClick) {
        detectTapGestures { pos ->
            layoutResult.value?.let { layoutResult ->
                onClick(layoutResult.getOffsetForPosition(pos))
            }
        }
    }

    Text(
        modifier = modifier.then(pressIndicator),
        text = text,
        color = color,
        textAlign = textAlign,
        maxLines = maxLines,
        softWrap = softWrap,
        overflow = overflow,
        fontFamily = textStyle.fontFamily,
        fontStyle = textStyle.fontStyle,
        fontWeight = textStyle.fontWeight,
        lineHeight = textStyle.lineHeight,
        onTextLayout = { textLayoutResult: TextLayoutResult ->
            layoutResult.value = textLayoutResult
            onTextLayout(textLayoutResult)
            val maxCurrentLineIndex: Int = textLayoutResult.lineCount - 1
            if (textLayoutResult.isLineEllipsized(maxCurrentLineIndex)) {
                textSize *= TEXT_SCALE_REDUCTION_INTERVAL
            }
        },
        fontSize = textSize,
    )
}

/**
 * pressClickEffect for TermsAgreementScreen
 */
enum class ButtonState { Pressed, Idle }
fun Modifier.bounceClick() = composed {
    var buttonState by remember { mutableStateOf(ButtonState.Idle) }
    val scale by animateFloatAsState(if (buttonState == ButtonState.Pressed) 0.95f else 1f)

    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(
                bounded = false,
                radius = 30.dp,
            ),
            onClick = { }
        )
        .pointerInput(buttonState) {
            awaitPointerEventScope {
                buttonState = if (buttonState == ButtonState.Pressed) {
                    waitForUpOrCancellation()
                    ButtonState.Idle
                } else {
                    awaitFirstDown(false)
                    ButtonState.Pressed
                }
            }
        }
}