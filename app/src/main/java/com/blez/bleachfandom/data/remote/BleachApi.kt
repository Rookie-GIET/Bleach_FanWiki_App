package com.blez.bleachfandom.data.remote

import com.blez.bleachfandom.domain.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BleachApi {
    @GET("/beach/heroes")
    suspend fun getAllHeroes( @Query("page") page :Int = 1) : ApiResponse

    @GET("/bleach/heroes/search")
    suspend fun searchHeroes(
        @Query("name") name : String
    ) : ApiResponse



}