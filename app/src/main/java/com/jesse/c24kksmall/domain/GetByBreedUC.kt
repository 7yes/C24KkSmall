package com.jesse.c24kksmall.domain

import android.util.Log
import com.jesse.c24kksmall.data.SmallRepo
import javax.inject.Inject

class GetByBreedUC @Inject constructor(private val repo: SmallRepo) {
    suspend operator fun invoke(breed: String): List<String> {
        Log.d("TAf", "invoke: ")
        return repo.getByBreed(breed)
    }
}