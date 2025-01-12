package com.example.randomuserapp.data.remote

import com.example.randomuserapp.domain.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
   // https://randomuser.me/api/?page=3&results=10&seed=abc
    @GET("api/?")
    suspend fun getApiResponse(
        @Query("page") page: Int,
        @Query("results") results: Int,
        @Query("seed") seed: String = "abc"
    ): ApiResponse

}