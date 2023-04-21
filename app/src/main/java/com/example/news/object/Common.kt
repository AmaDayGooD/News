package com.example.news.`object`

import com.example.news.Interface.RetrofitServices
import com.example.news.retrofit.RetrofitClient
import retrofit2.create

object Common {
    private val BASE_URL = "https://www.simplifiedcoding.net/demos/"
    val retrofitService : RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}
