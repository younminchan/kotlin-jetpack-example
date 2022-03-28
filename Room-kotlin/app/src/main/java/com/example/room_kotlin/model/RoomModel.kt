package com.example.room_kotlin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "room")
class RoomModel(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "value") var value: String
) {
    constructor() : this(0, "")
}