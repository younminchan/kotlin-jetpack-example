package com.example.databinding_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.databinding_kotlin.model.GitUserItem
import com.example.databinding_kotlin.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var retrofit = RetrofitService.getInstance()
        retrofit.requestRegUser("younminchan").enqueue(object : Callback<ArrayList<GitUserItem>> {
            override fun onResponse(call: Call<ArrayList<GitUserItem>>, response: Response<ArrayList<GitUserItem>>) {
                Log.e("YMC", "retrofit onResponse")
                if(response.isSuccessful){
                    var res = response.body()
                    if (res != null) {
                        for(item in res){
                            Log.e("YMC", "name: ${item.name} / full_name: ${item.fullName}")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<GitUserItem>>, t: Throwable) {
                Log.e("YMC", "retrofit onResponse: ${t.toString()}")
            }
        })
    }
}

/**
 * 작업 목적!
 * 1. Singleton Retrofit object
 * 2. Coroutine Scrope 활용
 * 3. Databinding 활용한 UI객체 연관성 줄이기
 *
 * */