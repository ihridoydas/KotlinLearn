package com.example.jetpackcomposepractice.RetrofitAPI.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposepractice.RetrofitAPI.Repository.DataRepository
import com.example.jetpackcomposepractice.RetrofitAPI.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataViewModel
@Inject
constructor(private val dataRepository: DataRepository) : ViewModel() {

    val response : MutableState<ApiState> = mutableStateOf(ApiState.Empty)

    init {
        getPost()
    }

    fun getPost() = viewModelScope.launch {
        dataRepository.getPost()
            .onStart {
                response.value = ApiState.Loading
            }
            .catch {
                response.value = ApiState.Failure(it)
            }
            .collect {
                    response.value = ApiState.Success(it)
            }
    }
}