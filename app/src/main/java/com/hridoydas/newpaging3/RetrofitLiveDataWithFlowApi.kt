package com.hridoydas.newpaging3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hridoydas.newpaging3.RecyclerViewPractice.RecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RetrofitLiveDataWithFlowApi : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

//    private lateinit var binding: ActivityRetrofitLiveDataWithFlowApiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_live_data_with_flow_api)
//        binding = ActivityRetrofitLiveDataWithFlowApiBinding.inflate(layoutInflater)
//        setContentView(binding.root)


        val actionBar = supportActionBar
        actionBar!!.title ="Live Data and Flow API"
        actionBar.setDisplayHomeAsUpEnabled(true)

    recyclerView= findViewById(R.id.rv)
    recyclerView.adapter = RecyclerViewAdapter()
    recyclerView.layoutManager = LinearLayoutManager(this)





    }



}