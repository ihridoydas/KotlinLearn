package com.hridoydas.newpaging3.Adapter.Item

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hridoydas.newpaging3.Adapter.Item.ItemModel
import com.hridoydas.newpaging3.Adapter.Item.ItemViewHolder
import com.hridoydas.newpaging3.R

class TestAdapter(private val onLoadMore:()->Unit): RecyclerView.Adapter<ItemViewHolder>(){

    val list = mutableListOf<ItemModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_item,parent,false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.itemModel = list[position]
        holder.updateView()
        if(position == list.size - 1){
            onLoadMore()
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun reload(list: MutableList<ItemModel>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()

    }

    //cloud brain -> reload method

    fun loadMore(list: MutableList<ItemModel>){
        this.list.addAll(list)
        notifyItemRangeChanged(this.list.size - list.size + 1,list.size)
    }
}