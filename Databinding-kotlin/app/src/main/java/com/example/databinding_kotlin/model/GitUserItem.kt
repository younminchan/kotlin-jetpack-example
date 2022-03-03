package com.example.databinding_kotlin.model


import com.google.gson.annotations.SerializedName

data class GitUserItem(
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("name")
    val name: String
)