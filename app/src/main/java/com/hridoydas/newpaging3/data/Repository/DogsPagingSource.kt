package com.hridoydas.newpaging3.data.Repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hridoydas.newpaging3.data.Model.TheDogApi.Dogs
import retrofit2.HttpException
import java.io.IOException

class DogsPagingSource constructor(private val apiService: ApiService) : PagingSource<Int, Dogs>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Dogs> {

        val page = params.key ?: 1
        return try {
            val response = apiService.getAllDogs(page, params.loadSize)
            LoadResult.Page(
                response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Dogs>): Int? {
        return null
    }
}