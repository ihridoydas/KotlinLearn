package com.hridoydas.hridoyapp.Data

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "remoteKeys")
data class RemoteKeys(
    @PrimaryKey
    val id:String,
    val prevKey:Int?,
    val nextKey:Int?
)
