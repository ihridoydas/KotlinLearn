package com.example.jetpackcomposepractice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.jetpackcomposepractice.ui.util.sideEffect.SideEffect

@Composable
fun PracticeScreen(
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Column {
            //Practice side Effect on jetpack compose (in Util section)
            SideEffect()

            /*

            //Button Effect
            //Animated Shape Touch
            AnimatedShapeTouch()

            //Ripple effect
            NoRippleEffect1()
            NoRippleEffect2()
            NoRippleEffect3()

            //PulsateEffect
            PulsateEffect()

            //Press Button
            PressEffect()

            //Shake Effect
            ShakeEffect()

             */

        }

    }
}
