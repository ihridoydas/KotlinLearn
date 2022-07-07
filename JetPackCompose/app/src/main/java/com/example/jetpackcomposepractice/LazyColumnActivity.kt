package com.example.jetpackcomposepractice

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Text
import com.example.jetpackcomposepractice.MainActivity

class LazyColumnActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            Button(onClick = {
//                val navigate = Intent(this@LazyColumnActivity,MainActivity::class.java)
//                startActivity(navigate)
//            }) {
//                Text(text = "Navigate Main Activity")
//            }


            //Start create Lazy Column


        }
    }
}
