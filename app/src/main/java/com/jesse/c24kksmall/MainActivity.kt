package com.jesse.c24kksmall

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jesse.c24kksmall.data.model.DogResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("TAf", "onStart: ")
        getDogs("african")
    }

    private fun getDogs(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(DogsApi::class.java).getByBreed("$query/images")
            val body =call.body()
            Log.d("TAf", "getByBreed: $body")
        }

        val dogService = getRetrofit().create(DogsApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val call = dogService.getByBreed("hound/images")
            val myData = call.body()
            Log.d("TAG", "getDogs: ${myData?.message}")
        }
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            //           .baseUrl("https://dog.ceo/api/breeds/image/") // getRandomDog
            .baseUrl("https://dog.ceo/api/breed/") // getbyBreed
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    interface DogsApi {
        @GET("random")
        suspend fun getRandomDog():Response<DogResponse>

        @GET
        suspend fun getByBreed(@Url url:String):Response<DogResponse>
    }
}