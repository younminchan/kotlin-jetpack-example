package com.example.databinding_kotlin.network

import com.example.databinding_kotlin.model.GitUserItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface RetrofitService {

    //git
//    https://api.github.com/users/younminchan/repos
    @Headers("Content-Type: application/json")
    @GET("users/{user}/repos")
    fun requestRegUser(
        @Path("user") user:String
    ): Call<ArrayList<GitUserItem>>

//    @Headers("Content-Type: application/json")
//    @GET("post")
//    suspend fun requestPostList(
//        @Query("user_id") user_id: Int? = null,
//        @Query("lon") lon: Double? = null,
//        @Query("lat") lat: Double? = null,
//        @Query("start") start: Int? = null,
//        @Query("end") end: Int? = null
//    ): Response<Any>

//    @FormUrlEncoded
//    @Headers("Content-Type: application/json")
//    @POST("login")
//    suspend fun requestLogin2(
//        @Field("email") email: String,
//        @Field("password") password: String
//    ): Response<LoginResponse>



    companion object {
        var networkService: RetrofitService? = null
        var baseUrl = "https://api.github.com/"

        fun getInstance(): RetrofitService {
            if (networkService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                networkService = retrofit.create(RetrofitService::class.java)
            }
            return networkService!!
        }
    }
}