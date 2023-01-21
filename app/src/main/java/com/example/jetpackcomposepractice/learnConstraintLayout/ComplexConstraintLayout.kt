import android.content.pm.ActivityInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.UiMode
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.common.LockScreenOrientation
import com.example.common.originalOrientation
import com.example.jetpackcomposepractice.R

@Composable
fun ConstainsLayout() {

    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

    //CircleWithCar
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (animationView) = createRefs()
        val createGuideTop = createGuidelineFromTop(5.dp)
        val createGuideBottom = createGuidelineFromBottom(5.dp)
        Box(
            modifier = androidx.compose.ui.Modifier
                .padding(20.dp)
                .constrainAs(animationView) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(createGuideTop)
                    bottom.linkTo(createGuideBottom)
                }
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            // 車の固定画像を表示する部分
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                // .padding(top = 5.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.damage_car_circle),
                    contentDescription = "",
                    modifier = Modifier
                )
            }

        }
    }

    //NumberWithCircle
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (numberCircle) = createRefs()
        val createGuideTop = createGuidelineFromTop(5.dp)
        val createGuideBottom = createGuidelineFromBottom(5.dp)
        Box(
            modifier = androidx.compose.ui.Modifier
                .padding(20.dp)
                .constrainAs(numberCircle) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(createGuideTop)
                    bottom.linkTo(createGuideBottom)
                }
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            // 回転部分
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(3.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box {
                    Image(
                        painter = painterResource(id = R.drawable.damage_circle_numbers),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                            .rotate(degrees = 20f)
                            .offset(y = -45.dp)
                    )
                }
            }



        }
    }

    //Icon
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (icon) = createRefs()

        // Camera icon images
        val cameraIconWhite = ImageBitmap.imageResource(id = R.drawable.damage_camera_icon_white)
        val cameraIconBlue = ImageBitmap.imageResource(id = R.drawable.damage_camera_icon_blue)
        val cameraIconGray = ImageBitmap.imageResource(id = R.drawable.damage_camera_icon_gray)
        val cameraIconArrow = ImageBitmap.imageResource(id = R.drawable.damage_arrow_icon)
        // Draw camera icon and arrow on canvas, use angle to work out position on circle circumference
        Box {
            Image(
                painter = painterResource(id = R.drawable.damage_camera_icon_blue),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = -45.dp)
            )
        }
    }

}

@Preview()
@Composable
fun ConstainsLayoutPreview() {
    ConstainsLayout()

}