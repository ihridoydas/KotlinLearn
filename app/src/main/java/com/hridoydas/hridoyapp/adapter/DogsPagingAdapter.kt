package com.hridoydas.hridoyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.hridoydas.hridoyapp.Data.Dogs
import com.hridoydas.hridoyapp.databinding.EachRowBinding
import javax.inject.Inject

class DogsPagingAdapter @Inject constructor() : PagingDataAdapter<Dogs,DogsPagingAdapter.DogsViewHolder>(DiffUtils) {

    class DogsViewHolder(val binding :EachRowBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(dogs: Dogs){
           binding.apply {
               image.load(dogs.url)
           }
        }

    }

    object DiffUtils : DiffUtil.ItemCallback<Dogs>(){
        override fun areItemsTheSame(oldItem: Dogs, newItem: Dogs): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Dogs, newItem: Dogs): Boolean {
           return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {
        val dog = getItem(position)
        if(dog != null){
            holder.bind(dog)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder {
        return DogsViewHolder(EachRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
}