package com.hridoydas.newpaging3.RecyclerViewPractice

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hridoydas.newpaging3.R

class RecyclerViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

    val itemName: TextView = itemView.findViewById(R.id.textViewRv)
}