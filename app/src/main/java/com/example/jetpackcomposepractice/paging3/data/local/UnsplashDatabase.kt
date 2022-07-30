package com.example.jetpackcomposepractice.paging3.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetpackcomposepractice.paging3.data.local.dao.UnsplashImageDao
import com.example.jetpackcomposepractice.paging3.data.local.dao.UnsplashRemoteKeysDao
import com.example.jetpackcomposepractice.paging3.model.UnsplashImage
import com.example.jetpackcomposepractice.paging3.model.UnsplashRemoteKeys

@Database(
    entities = [UnsplashImage::class, UnsplashRemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class UnsplashDatabase : RoomDatabase() {

    abstract fun unsplashImageDao(): UnsplashImageDao
    abstract fun unsplashRemoteKeysDao(): UnsplashRemoteKeysDao

}