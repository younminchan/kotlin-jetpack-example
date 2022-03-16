package com.example.viewmodel_kotlin

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel

class MainViewModel(userId: String) : ViewModel() {
    var strViewModel = "ViewModelTest"

    /** ViewModel Singleton? */
    companion object{
        private lateinit var instance: MainViewModel

        @MainThread
        fun getInstance(userId: String): MainViewModel{
            instance = if(::instance.isInitialized) instance else MainViewModel(userId)
            return instance
        }
    }
}