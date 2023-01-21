/*
 * Created by masaki on 2022/09/26
 * Last modified 2022/09/14 11:22
 * Copyright © 2022 Cognivision Inc. All rights reserved.
 */

package jp.cognivision.cpbmobile.util.extensions

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.net.ConnectivityManager
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import java.util.concurrent.Executor
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Created by DavidA on 2022/05/17.
 * Copyright © 2022 Cognivision inc. All rights reserved.
 */

/**
 * Find Activity on Context
 */
fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

/**
 * ネットワーク確認
 * @return 接続時はtrue, 未接続の場合はfalseを返す。
 */
fun Context.isNetworkConnected() =
    (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).let {
        val capabilities = it.getNetworkCapabilities(it.activeNetwork)
        capabilities != null
    }

/**
 * Get a camera provider
 */
suspend fun Context.getCameraProvider(): ProcessCameraProvider = suspendCoroutine { continuation ->
    ProcessCameraProvider.getInstance(this).also { future ->
        future.addListener({
            continuation.resume(future.get())
        }, executor)
    }
}

val Context.executor: Executor
    get() = ContextCompat.getMainExecutor(this)
