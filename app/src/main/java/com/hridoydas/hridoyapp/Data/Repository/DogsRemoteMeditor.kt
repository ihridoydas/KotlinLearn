package com.hridoydas.hridoyapp.Data.Repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.Database
import androidx.room.withTransaction
import com.hridoydas.hridoyapp.Data.Dogs
import com.hridoydas.hridoyapp.Data.RemoteKeys
import com.hridoydas.hridoyapp.Data.database.DogsDatabase
import com.hridoydas.hridoyapp.Network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.internal.wait
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class DogsRemoteMeditor
constructor(
    private val db: DogsDatabase,
    private val apiService: ApiService
) : RemoteMediator<Int, Dogs>() {
    private val INDEX = 1
    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Dogs>):
            MediatorResult {
        val page = when (val pageData = getKeysPageData(loadType, state)) {

            is MediatorResult.Success -> {
                return pageData

            }
            else -> {
                pageData as Int
            }
        }
        try {
            val response = apiService.getAllDogs(page, state.config.pageSize)
            val isEndOfList = response.isEmpty()

            db.withTransaction{
                if (loadType == LoadType.REFRESH){
                    db.getDogSDao().clearAll()
                    db.getRemoteKeysDao().clearAll()
                }
                val prevKey = if(page == INDEX) null else page-1
                val nextKey = if(isEndOfList) null else page+1
                val Keys = response.map {
                    RemoteKeys(it.id,prevKey,nextKey)
                }
                db.getRemoteKeysDao().insertAll(Keys)
                db.getDogSDao().insert(response)
            }
            return MediatorResult.Success(endOfPaginationReached = isEndOfList)
        }catch (e:IOException){
            return MediatorResult.Error(e)
        }catch (e:HttpException){
            return MediatorResult.Error(e)
        }
    }


    private suspend fun getKeysPageData(loadType: LoadType, state: PagingState<Int, Dogs>): Any {

        return when (loadType) {
            LoadType.REFRESH -> {
                val remotekey = getRefreshRemoteKeys(state)
                remotekey?.nextKey?.minus(1) ?: INDEX
            }
            LoadType.PREPEND -> {
                val remotekey = getFirstRemoteKey(state)
                val prevkey = remotekey?.prevKey ?: MediatorResult.Success(
                    endOfPaginationReached = false
                )
                prevkey
            }

            LoadType.APPEND -> {
                val remotekey = getLastRemoteKey(state)
                val nextkey = remotekey?.nextKey ?: MediatorResult.Success(
                    endOfPaginationReached = true
                )
                nextkey

            }
        }
    }

    private suspend fun getFirstRemoteKey(state: PagingState<Int, Dogs>): RemoteKeys? {
        return withContext(Dispatchers.IO) {
            state.pages
                .firstOrNull {
                    it.data.isNotEmpty()
                }?.data?.firstOrNull()
                ?.let { dogs ->
                    db.getRemoteKeysDao().getAllRemoteKeys(dogs.id)
                }
        }
    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, Dogs>): RemoteKeys? {
        return withContext(Dispatchers.IO) {
            state.pages
                .lastOrNull() {
                    it.data.isNotEmpty()
                }?.data?.lastOrNull()
                ?.let { dogs ->
                    db.getRemoteKeysDao().getAllRemoteKeys(dogs.id)
                }
        }
    }

    private suspend fun getRefreshRemoteKeys(state: PagingState<Int, Dogs>): RemoteKeys? {
        return withContext(Dispatchers.IO) {
            state.anchorPosition?.let { position ->
                state.closestItemToPosition(position)?.id?.let { id ->
                    db.getRemoteKeysDao().getAllRemoteKeys(id)

                }


            }
        }
    }
}