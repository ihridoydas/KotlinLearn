package com.example.jetpackcomposepractice.RetrofitAPI.Network

import com.example.jetpackcomposepractice.RetrofitAPI.model.Post
import retrofit2.http.GET

interface Apiservice {

    companion object{
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    @GET("posts/")
    suspend fun getPost():List<Post>
}