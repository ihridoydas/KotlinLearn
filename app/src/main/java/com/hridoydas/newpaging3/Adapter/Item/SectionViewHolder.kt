package com.hridoydas.newpaging3.Adapter.Item

import android.content.Context
import android.widget.FrameLayout
import android.widget.TextView
import com.hridoydas.newpaging3.R

class SectionViewHolder(
    context: Context
)
    :FrameLayout(context) {

        private lateinit var textViewDate : TextView

     init {
         inflate(context, R.layout.view_holder_section,this)
         findView()
     }

    private fun findView(){
        textViewDate = findViewById(R.id.textViewDate)

    }

    fun setDate(dateString:String){
        textViewDate.text = dateString
    }

}