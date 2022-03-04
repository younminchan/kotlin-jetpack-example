package com.example.databinding_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.databinding_kotlin.adapter.MyAdapter
import com.example.databinding_kotlin.databinding.ActivityMainBinding
import com.example.databinding_kotlin.model.GitUserItem
import com.example.databinding_kotlin.network.RetrofitService
import com.example.databinding_kotlin.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //DataBinding
        binding.mainActivity = this
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewModel = mainViewModel

        //ViewModel LifeCycle에 종속, LifeCycle-Observe역할
        binding.lifecycleOwner = this //TODO: 반드시 필요

        //RvAdapter 설정
//        binding.rvList.layoutManager = LinearLayoutManager(this)
    }

    fun Gitsearch(){
        mainViewModel.initRetrofit(binding.etSearch, binding.etSearch.text.toString())
    }
}