package com.example.room_kotlin.dao-

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.room_kotlin.model.RoomModel

@Dao
interface RoomDao {

    @Query("SELECT * FROM room")
    fun getAll(): LiveData<List<RoomModel>>

    @Insert(onConflict = REPLACE)
    fun insert(roomModel: RoomModel)

    @Query("DELETE FROM room")
    fun deleteAll()
}