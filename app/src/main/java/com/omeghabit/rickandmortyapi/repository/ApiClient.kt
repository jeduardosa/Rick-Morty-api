package com.omeghabit.rickandmortyapi.repository

import com.omeghabit.rickandmortyapi.model.adapter.ApiResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object ApiClient {
    private const val BASE_URL = "https://rickandmortyapi.com/api/"
    private val retrofit by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
interface ApiService {
        @GET("character")
        suspend fun getCharacters(): ApiResponse
    }
