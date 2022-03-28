package com.example.room_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.room_kotlin.dao.RoomDB
import com.example.room_kotlin.dao.RoomDao
import com.example.room_kotlin.databinding.ActivityMainBinding
import com.example.room_kotlin.model.RoomModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var roomDao: RoomDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        roomDao = RoomDB.getInstance(applicationContext)!!.roomDao()


        /** Room Insert */
        binding.tvInput.setOnClickListener {
            if (!binding.etInput.text.isNullOrEmpty()) {
                var text = binding.etInput.text.toString()
                var model = RoomModel(0, text)

                CoroutineScope(Dispatchers.IO).launch {
                    roomDao.insert(model)
                }
            } else {
                Toast.makeText(this, "value를 입력해 주세요", Toast.LENGTH_SHORT).show()
            }
        }

        /** Room Delete */
        binding.tvDeleteAll.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                roomDao.deleteAll()
            }
        }

        /** Room Select(LiveData) */
        roomDao.getAll().observe(this, Observer {
            var text = ""
            for(item in it){
                var itemText = "id: ${item.id} / value: ${item.value}\n"
                text += itemText
            }

            binding.tvResult.text = "$text"
        })
    }
}