package com.hridoydas.newpaging3

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RetrofitLiveDataWithFlowApi : AppCompatActivity() {

//    private lateinit var binding: ActivityRetrofitLiveDataWithFlowApiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_live_data_with_flow_api)
//        binding = ActivityRetrofitLiveDataWithFlowApiBinding.inflate(layoutInflater)
//        setContentView(binding.root)


        val actionBar = supportActionBar
        actionBar!!.title ="Live Data and Flow API"
        actionBar.setDisplayHomeAsUpEnabled(true)




    }



}