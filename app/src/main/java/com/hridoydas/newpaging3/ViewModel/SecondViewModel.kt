package com.hridoydas.newpaging3.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hridoydas.newpaging3.data.Model.TheDogApi.Dogs
import com.hridoydas.newpaging3.data.Repository.ApiService
import com.hridoydas.newpaging3.data.Repository.DogsPagingSource

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SecondViewModel
@Inject
constructor(private val apiService: ApiService) : ViewModel() {
    fun getAllDogs(): Flow<PagingData<Dogs>> = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false),

        ) {
        DogsPagingSource(apiService)
    }.flow.cachedIn(viewModelScope)

}