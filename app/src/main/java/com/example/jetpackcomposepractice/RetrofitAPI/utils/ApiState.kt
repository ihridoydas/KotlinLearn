package com.example.jetpackcomposepractice.RetrofitAPI.utils

import com.example.jetpackcomposepractice.RetrofitAPI.model.Post

sealed class ApiState{
    class Success(val data:List<Post>) :ApiState()
    class Failure(val msg:Throwable):ApiState()
    object Loading :ApiState()
    object Empty :ApiState()

}
