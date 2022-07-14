package com.example.jetpackcomposepractice.viewPager

import androidx.annotation.DrawableRes
import com.example.jetpackcomposepractice.R

data class Pager(
    @DrawableRes val image:Int,
    val des:String
)
val datalist = listOf(
    Pager(R.drawable.page_one,"PAGE ONE"),
    Pager(R.drawable.page_two,"PAGE TWO"),
    Pager(R.drawable.page_one,"PAGE THREE")

)