package com.example.randomuserapp.data.remote

import com.example.randomuserapp.domain.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("?results=5000")
    suspend fun getApiResponse(@Query("page") page: Int,
                               @Query("pageSize") pageSize: Int):ApiResponse


}