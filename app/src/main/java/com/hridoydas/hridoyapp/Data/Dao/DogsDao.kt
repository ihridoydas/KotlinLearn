package com.hridoydas.hridoyapp.Data.Dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hridoydas.hridoyapp.Data.Dogs

@Dao
interface DogsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dogs:List<Dogs>)

    @Query("SELECT * FROM dogs")
    fun getAll():PagingSource<Int,Dogs>

    @Query("DELETE FROM dogs")
    fun clearAll()
}