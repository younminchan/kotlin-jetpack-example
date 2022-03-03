package com.example.databinding_kotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.databinding_kotlin.databinding.UserItemBinding
import com.example.databinding_kotlin.model.GitUserItem

class MyAdapter() : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    var userList = arrayListOf<GitUserItem>()

    // 생성된 뷰 홀더에 값 지정
    class MyViewHolder(private val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentUser: GitUserItem) {
            binding.user = currentUser
        }
    }

    // 어떤 xml 으로 뷰 홀더를 생성할지 지정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    // 뷰 홀더에 데이터 바인딩
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    // 뷰 홀더의 개수 리턴
    override fun getItemCount(): Int {
        return userList.size
    }

    /** 깜빡임 방지
     * 1. getItemId로 지정
     * 2. Adapter에 setHasStableIds(true) 지정
     * */
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}