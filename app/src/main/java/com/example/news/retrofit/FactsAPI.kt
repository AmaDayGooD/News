package com.example.news.retrofit

import retrofit2.http.*

interface FactsAPI {
    @GET("spells/{id}")
    suspend fun getFact(@Path("id") id: Int) : DataFacts
}