package com.example.jetpackcomposepractice.retrofitAPI.utils

import com.example.jetpackcomposepractice.retrofitAPI.model.Post

sealed class ApiState{
    class Success(val data:List<Post>) :ApiState()
    class Failure(val msg:Throwable):ApiState()
    object Loading :ApiState()
    object Empty :ApiState()

}
