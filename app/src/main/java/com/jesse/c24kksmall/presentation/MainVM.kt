package com.jesse.c24kksmall.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jesse.c24kksmall.domain.GetByBreedUC
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(private val getByBreedUC: GetByBreedUC) : ViewModel() {
    private val _data = MutableLiveData<List<String>>()
    val data: LiveData<List<String>> = _data


     suspend fun getByBreed(breed: String): List<String> {
        val myData = getByBreedUC(breed)
         _data.postValue(myData)
        Log.d("TAf", "en el init $myData ")
        return myData
    }
}