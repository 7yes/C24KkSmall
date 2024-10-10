package com.jesse.c24kksmall.data.network

import android.util.Log
import javax.inject.Inject

class SmallService @Inject constructor(private val api: SmallApi) {
    suspend fun getByBreed(breed: String): List<String> {
        val response = api.getByBreed(breed)
        Log.d("TAf ", "service getByBreed: ${response.body()?.message} ")
        return response.body()?.message ?: emptyList()
    }
}