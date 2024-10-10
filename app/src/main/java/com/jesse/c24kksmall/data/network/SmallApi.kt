package com.jesse.c24kksmall.data.network

import com.jesse.c24kksmall.data.model.DogResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface SmallApi {

    @GET
    suspend fun getByBreed(@Url url:String): Response<DogResponse>
}