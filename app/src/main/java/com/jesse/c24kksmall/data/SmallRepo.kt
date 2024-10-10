package com.jesse.c24kksmall.data

import android.util.Log
import com.jesse.c24kksmall.data.network.SmallApi
import com.jesse.c24kksmall.data.network.SmallService
import javax.inject.Inject

class SmallRepo @Inject constructor(private val service: SmallService) {
    suspend fun getByBreed(breed: String): List<String> {
        val response: List<String> = service.getByBreed(breed)
        Log.d("TAf ", "repo getByBreed: $response ")
        return response
    }
}