package com.example.databinding_kotlin.adapter

import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.databinding_kotlin.model.GitUserItem

object MyBindingAdapter{

    @BindingAdapter("rv_items")
    @JvmStatic
    fun rv_items(recyclerView: RecyclerView, items : ArrayList<GitUserItem>){
        if(recyclerView.adapter == null) {
            val adapter = MyAdapter()
            adapter.setHasStableIds(true)
            recyclerView.adapter = adapter
        }

        val myAdapter = recyclerView.adapter as MyAdapter
        myAdapter.userList = items
        myAdapter.notifyDataSetChanged()
    }

    @BindingAdapter("search")
    @JvmStatic
    fun gitSearch(textView: TextView, str: String){

    }
}