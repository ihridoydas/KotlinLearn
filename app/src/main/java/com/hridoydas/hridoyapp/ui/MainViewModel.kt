package com.hridoydas.hridoyapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.hridoydas.hridoyapp.Data.Dogs
import com.hridoydas.hridoyapp.Data.Repository.DogsRemoteMeditor
import com.hridoydas.hridoyapp.Data.database.DogsDatabase
import com.hridoydas.hridoyapp.Network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
    constructor(private val db:DogsDatabase,private val apiService: ApiService)
    : ViewModel() {

        @OptIn(ExperimentalPagingApi::class)
        fun getAllDogs() : Flow<PagingData<Dogs>> = Pager(
            config = PagingConfig(50, enablePlaceholders = false),
            remoteMediator = DogsRemoteMeditor(db,apiService)
        ){
            db.getDogSDao().getAll()
        }.flow.cachedIn(viewModelScope)
}