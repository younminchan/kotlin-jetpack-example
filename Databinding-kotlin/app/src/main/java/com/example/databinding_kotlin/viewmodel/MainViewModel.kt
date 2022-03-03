package com.example.databinding_kotlin.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.databinding_kotlin.model.GitUserItem
import com.example.databinding_kotlin.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    var retrofit = RetrofitService.getInstance()
    private var items = ArrayList<GitUserItem>()

    //검색어
    private val _searchUser = MutableLiveData<String>()
    val searchUser: LiveData<String>
        get() = _searchUser

    //검색결과
    private val _userItemList = MutableLiveData<ArrayList<GitUserItem>>()
    val userList : LiveData<ArrayList<GitUserItem>>
        get() = _userItemList

    init{
        items = arrayListOf(
            GitUserItem("fullName", "name")
        )
        _userItemList.value = items
    }


    fun buttonClick(){
        initRetrofit(_searchUser.value.toString())

//        items.add(GitUserItem("fullName2", "name2"))
//        _userItemList.value = items
    }

    fun initRetrofit(searchText:String){
        retrofit.requestRegUser(searchText).enqueue(object : Callback<ArrayList<GitUserItem>> {
            override fun onResponse(call: Call<ArrayList<GitUserItem>>, response: Response<ArrayList<GitUserItem>>) {
                Log.e("YMC", "retrofit onResponse")
                if(response.isSuccessful){
                    var res = response.body()

                    if (res != null) {
                        for(item in res){
                            Log.e("YMC", "name: ${item.name} / full_name: ${item.fullName}")
                        }

                        items.addAll(res)
                        _userItemList.value = items
                        _searchUser.value = ""
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<GitUserItem>>, t: Throwable) {
                Log.e("YMC", "retrofit onResponse: ${t.toString()}")
            }
        })
    }
}