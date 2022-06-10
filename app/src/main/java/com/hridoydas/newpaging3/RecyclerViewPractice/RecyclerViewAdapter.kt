package com.hridoydas.newpaging3.RecyclerViewPractice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hridoydas.newpaging3.R



class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewHolder>(){

    val animalList = listOf(
        "Tiger","Monkey","Cat","Rat","Dog","Elephant","Small Dog","Small cat","Snack","Big Dog","Lion",
        "Tiger","Monkey","Cat","Rat","Dog","Elephant","Small Dog","Small cat","Snack","Big Dog","Lion",
        "Tiger","Monkey","Cat","Rat","Dog","Elephant","Small Dog","Small cat","Snack","Big Dog","Lion",
        "Tiger","Monkey","Cat","Rat","Dog","Elephant","Small Dog","Small cat","Snack","Big Dog","Lion"


    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {

        val itemXml = LayoutInflater.from(parent.context).inflate(R.layout.one_layout,parent,false)

        return RecyclerViewHolder(itemXml)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

         holder.itemName.text = animalList[position]

    }

    override fun getItemCount(): Int {
       return animalList.size
    }
}