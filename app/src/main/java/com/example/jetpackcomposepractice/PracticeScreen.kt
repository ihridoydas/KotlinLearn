package com.example.jetpackcomposepractice

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.example.jetpackcomposepractice.customDialog.DialogSample
import com.example.jetpackcomposepractice.ui.theme.JetPackComposePracticeTheme

@Composable
fun PracticeScreen(content: @Composable () -> Unit) {

    JetPackComposePracticeTheme {
        Surface(color = MaterialTheme.colors.background) {


            //content()
            DialogSample()
        }

    }


//    Column(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Top
//    ) {
//        Column {
//            //Practice side Effect on jetpack compose (in Util section)
//            //SideEffect()
//
//            /*
//
//            //Button Effect
//            //Animated Shape Touch
//            AnimatedShapeTouch()
//
//            //Ripple effect
//            NoRippleEffect1()
//            NoRippleEffect2()
//            NoRippleEffect3()
//
//            //PulsateEffect
//            PulsateEffect()
//
//            //Press Button
//            PressEffect()
//
//            //Shake Effect
//            ShakeEffect()
//
//             */
//
//        }
//
//    }
}
