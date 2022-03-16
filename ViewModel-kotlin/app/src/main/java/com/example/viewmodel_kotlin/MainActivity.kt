package com.example.viewmodel_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodel_kotlin.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        /** ViewModel 설정*/
        mainViewModel = MainViewModel.getInstance("viewModel")

        /** viewModel 값 가져와서 처리 */
        binding.tvText.text = mainViewModel.strViewModel
    }
}