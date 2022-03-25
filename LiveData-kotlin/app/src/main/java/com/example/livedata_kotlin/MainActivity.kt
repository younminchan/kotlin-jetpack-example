package com.example.livedata_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.livedata_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var liveText: MutableLiveData<String> = MutableLiveData()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        liveDataInit()
    }

    private fun liveDataInit(){
        /** EditText변경에 따른 listener추가 */
        binding.etInput.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                liveText.value = p0.toString()
            }
        })

        /** EditText 변경에 따른 TextView 설정*/
        liveText.observe(this, Observer {
            binding.tvResult.text = "Livedata 값: $it"
        })
    }
}