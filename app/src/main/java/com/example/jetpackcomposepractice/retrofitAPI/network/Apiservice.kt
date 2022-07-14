package com.example.jetpackcomposepractice.retrofitAPI.network

import com.example.jetpackcomposepractice.retrofitAPI.model.Post
import retrofit2.http.GET

interface Apiservice {

    companion object{
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    @GET("posts/")
    suspend fun getPost():List<Post>
}