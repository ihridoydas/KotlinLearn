package com.hridoydas.hridoyapp.Data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "dogs")
data class Dogs(
    @PrimaryKey
    @Json(name="id")
    val id:String,
    @Json(name= "url")
    val url:String
)
