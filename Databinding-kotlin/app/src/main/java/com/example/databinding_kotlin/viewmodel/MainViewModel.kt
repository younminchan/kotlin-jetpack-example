package com.example.databinding_kotlin.viewmodel

import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.databinding_kotlin.App
import com.example.databinding_kotlin.model.GitUserItem
import com.example.databinding_kotlin.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    var retrofit = RetrofitService.getInstance()
    private var items = ArrayList<GitUserItem>()

    //검색결과
    private val _userItemList = MutableLiveData<ArrayList<GitUserItem>>()
    val userList : LiveData<ArrayList<GitUserItem>>
        get() = _userItemList

    init{
        items = arrayListOf()
        _userItemList.value = items
    }


    fun initRetrofit(textView:TextView, searchText: String){
        Log.e("YMC", "검색어: ${searchText}")
        retrofit.requestRegUser(searchText).enqueue(object : Callback<ArrayList<GitUserItem>> {
            override fun onResponse(call: Call<ArrayList<GitUserItem>>, response: Response<ArrayList<GitUserItem>>) {
                if(response.isSuccessful){
                    var res = response.body()
                    if (res != null && res.size>0) {
//                        for(item in res){
//                            Log.e("YMC", "name: ${item.name} / full_name: ${item.fullName}")
//                        }

                        insertSearchItem(res, textView)
                    }
                    else{
                        insertSearchItem(ArrayList(), textView)
                        Toast.makeText(App.activity, "검색결과가 없습니다.", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    insertSearchItem(ArrayList(), textView)
                    Toast.makeText(App.activity, "검색에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ArrayList<GitUserItem>>, t: Throwable) {
                Log.e("YMC", "retrofit onResponse: ${t.toString()}")
                insertSearchItem(ArrayList(), textView)
                Toast.makeText(App.activity, "검색에 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun insertSearchItem(arr:ArrayList<GitUserItem>, textView: TextView){
        items.clear()
        if(arr!=null && arr.size>0){
            items.addAll(arr)
        }
        _userItemList.value = items //검색결과 추가
        textView.text = ""
    }

    fun buttonClick(){
        //함수호출은 가능하지만, 변수를 담은 함수호춟방법에 대해서는 아직 정확히 습득을 못한 상태..
    }
}