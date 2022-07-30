package com.example.jetpackcomposepractice.paging3.data.remote

import com.example.jetpackcomposepractice.BuildConfig
import com.example.jetpackcomposepractice.paging3.model.SearchResult
import com.example.jetpackcomposepractice.paging3.model.UnsplashImage
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface UnsplashApi {

    //EndPoint of api unsplash-> /photos
    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("/photos")
    suspend fun getAllImages(
        @Query("page") page: Int,
        @Query("per_page") perpage: Int
    ): List<UnsplashImage>


    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("/search/photos")
    suspend fun searchImages(
        @Query("query") query: String,
        @Query("per_page") perpage: Int
    ): SearchResult

}