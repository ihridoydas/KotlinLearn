/*
 * Created by masaki on 2022/09/26
 * Last modified 2022/09/26 11:12
 * Copyright © 2022 Cognivision Inc. All rights reserved.
 */

package jp.cognivision.cpbmobile.util.common

import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import jp.cognivision.cpbmobile.util.extensions.findActivity

/**
 * Created by DavidA on 2022/05/17.
 * Copyright © 2022 Cognivision inc. All rights reserved.
 */

var originalOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
/**
 * Lock a screen to orientation
 * @param orientation The orientation to lock screen to
 */
@Composable
fun LockScreenOrientation(orientation: Int) {
    val context = LocalContext.current
    DisposableEffect(Unit) {
        val activity = context.findActivity() ?: return@DisposableEffect onDispose {}
        originalOrientation = activity.requestedOrientation
        activity.requestedOrientation = orientation
        onDispose {
            // restore original orientation when view disappears
//            activity.requestedOrientation = originalOrientation
        }
    }
}
