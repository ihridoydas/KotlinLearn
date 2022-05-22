package com.hridoydas.hridoyapp.Data.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hridoydas.hridoyapp.Data.model.RemoteKeys

@Dao
interface RemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(dogs:List<RemoteKeys>)

    @Query("SELECT * FROM remoteKeys WHERE id =:id")
    fun getAllRemoteKeys(id:String) : RemoteKeys

    @Query("DELETE FROM remoteKeys")
    fun clearAll()
}