package com.example.databinding_kotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.databinding_kotlin.model.GitUserItem

class MainViewModel : ViewModel() {
    private val _userItemList = MutableLiveData<ArrayList<GitUserItem>>()
    val userList : LiveData<ArrayList<GitUserItem>>
        get() = _userItemList


    private var items = ArrayList<GitUserItem>()

    init{
        items = arrayListOf(
            GitUserItem("fullName", "name")
        )
        _userItemList.value = items
    }

    fun buttonClick(){
        items.add(GitUserItem("fullName2", "name2"))
        _userItemList.value = items
    }
}