package com.hridoydas.newpaging3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.hridoydas.newpaging3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar =supportActionBar
        actionBar!!.title= "All Project"

        binding.hilt.setOnClickListener {
            val intent = Intent(this,SecondActivity::class.java)
            startActivity(intent)

        }

        binding.RetofitLiveDataFlow.setOnClickListener{

            val retrofitlivedataIntent = Intent(this,RetrofitLiveDataWithFlowApi::class.java)
            startActivity(retrofitlivedataIntent)
        }





    }

}