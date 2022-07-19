package com.example.jetpackcomposepractice.albumFeature.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Images")
class Images(
    @PrimaryKey val id: Int = 0,
    @ColumnInfo(name = "albumId") val albumId: Int = 0,
    @ColumnInfo(name = "category") val category: String = "",
    @ColumnInfo(name = "orderNumber") val orderNumber: Int = 1,
    @ColumnInfo(name = "path") val path: String? = "",
    @ColumnInfo(name = "fileName") val fileName: String = "",
    @ColumnInfo(name = "fileNameLight") val fileNameLight: String? = "",
    @ColumnInfo(name = "isUploaded") val isUploaded: Boolean = false,
    @ColumnInfo(name = "isDeleted") val isDeleted: Boolean = false,
    @ColumnInfo(name = "created") val created: Date? = Date(),
    @ColumnInfo(name = "modified") val modified: Date? = Date(),
)
