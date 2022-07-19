package com.example.jetpackcomposepractice.albumFeature.util

import androidx.compose.ui.graphics.Color

class ColorHex{
    companion object{
        fun hex2Rgb(colorStr: String): Color {
            return Color(
                Integer.valueOf(colorStr.substring(1, 3), 16),
                Integer.valueOf(colorStr.substring(3, 5), 16),
                Integer.valueOf(colorStr.substring(5, 7), 16)
            )
        }

    }
}