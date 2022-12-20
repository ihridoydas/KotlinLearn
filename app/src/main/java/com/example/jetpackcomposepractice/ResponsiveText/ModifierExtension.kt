/*
 * Created by masaki on 2022/09/26
 * Last modified 2022/08/31 14:03
 * Copyright © 2022 Cognivision Inc. All rights reserved.
 */

package com.example.jetpackcomposepractice.ResponsiveText

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by DavidA on 2022/07/04.
 * Copyright © 2022 Cognivision inc. All rights reserved.
 */
fun Modifier.conditional(condition : Boolean, modifier : Modifier.() -> Modifier) : Modifier {
    return if (condition) {
        modifier.invoke(this)
    } else {
        this
    }
}

/**
 * Rippleエフェクト無しクリック
 * @param enabled 有効無効
 * @param onClickLabel ラベル
 * @param role Role
 * @param onClick クリック処理
 * @return Modifier
 */
fun Modifier.clickableNoRipple(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit
): Modifier = composed {
    this.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role,
        onClick = onClick
    )
}


/**
 * Camera guide in shrunk mode
 * @param expandTap callback on expand button tap
 */
@Composable
fun cameraGuideText(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .padding(horizontal = 150.dp)
            .clip(RoundedCornerShape(10.dp))
            .height(50.dp)
            .background(Color.White)
            .clickableNoRipple() { /*This is just to consume clicks from camera tap*/ },
        contentAlignment = Alignment.Center
    ) {
        Text(text, fontSize = 16.sp, textAlign = TextAlign.Center )
    }
}