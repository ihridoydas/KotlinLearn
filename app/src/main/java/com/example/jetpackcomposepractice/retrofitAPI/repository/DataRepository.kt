package com.example.jetpackcomposepractice.retrofitAPI.repository

import com.example.jetpackcomposepractice.retrofitAPI.network.Apiservice
import com.example.jetpackcomposepractice.retrofitAPI.model.Post
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