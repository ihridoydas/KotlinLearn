package com.hridoydas.hridoyapp.Data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hridoydas.hridoyapp.Data.Dao.DogsDao
import com.hridoydas.hridoyapp.Data.Dao.RemoteKeysDao
import com.hridoydas.hridoyapp.Data.Dogs
import com.hridoydas.hridoyapp.Data.RemoteKeys

@Database(entities = [Dogs::class,RemoteKeys::class], version = 1, exportSchema = false)
abstract class DogsDatabase : RoomDatabase(){
    abstract fun getDogSDao(): DogsDao
    abstract fun getRemoteKeysDao(): RemoteKeysDao
}