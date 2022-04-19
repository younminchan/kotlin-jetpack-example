package com.example.lifecycles_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lifecycles_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var lifecycleObserver = MyObserver(lifecycle)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycle.addObserver(lifecycleObserver)
    }
}