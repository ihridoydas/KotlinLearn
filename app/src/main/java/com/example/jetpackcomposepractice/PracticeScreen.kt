package com.example.jetpackcomposepractice

import ConstainsLayout
import android.content.pm.ActivityInfo
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.example.common.LockScreenOrientation
import com.example.jetpackcomposepractice.Component.Text.MiddleEllipsisText
import com.example.jetpackcomposepractice.learnConstraintLayout.ConstraintLayoutSample
import com.example.jetpackcomposepractice.stickyHeaderWithLazyColumn.StickyHeaderScreen
import com.example.jetpackcomposepractice.ui.theme.JetPackComposePracticeTheme

@Composable
fun PracticeScreen(content: @Composable () -> Unit) {

    JetPackComposePracticeTheme {
        Surface(color = MaterialTheme.colors.background) {
            LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

           // StickyHeaderScreen()

            //content()
            // DialogSample()

            //ellipsis in the middle of text
            /*MiddleEllipsisText(
                text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
            )*/

           // ConstraintLayoutSample()
            ConstainsLayout()
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
