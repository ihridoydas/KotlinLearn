package com.example.jetpackcomposepractice.RetrofitAPI.Repository

import com.example.jetpackcomposepractice.RetrofitAPI.Network.Apiservice
import com.example.jetpackcomposepractice.RetrofitAPI.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DataRepository
@Inject
constructor(private val apiservice: Apiservice) {
    fun getPost():Flow<List<Post>> = flow{
        emit(apiservice.getPost())
    }.flowOn(Dispatchers.IO)
}