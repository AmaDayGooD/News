package com.example.news.retrofit

import retrofit2.http.*

interface FactsAPI {
    @GET("fact?max_length=70")
    suspend fun getFact() : DataFacts
}