package com.hridoydas.newpaging3.data.Model.TheDogApi

import com.squareup.moshi.Json

data class Dogs(
    @Json(name = "url")
    val url: String
)
