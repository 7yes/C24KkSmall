package com.jesse.c24kksmall

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jesse.c24kksmall.data.model.DogResponse
import com.jesse.c24kksmall.databinding.ActivityMainBinding
import com.jesse.c24kksmall.presentation.MainVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("TAf", "onStart: ")
        //getDogs("african")
//        CoroutineScope(Dispatchers.IO).launch {
//            viewModel.getByBreed("african/images")
//        }
    }

    private fun getDogs(query: String) {
        val dogService = getRetrofitb().create(DogsApib::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val call = dogService.getByBreed("$query/images")
            val myData = call.body()
            Log.d("TAf", "getDogs: ${myData?.message}")
        }
    }

    fun getRetrofitb(): Retrofit {
        return Retrofit.Builder()
            //           .baseUrl("https://dog.ceo/api/breeds/image/") // getRandomDog
            .baseUrl("https://dog.ceo/api/breed/") // getbyBreed
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    interface DogsApib {
        @GET("random")
        suspend fun getRandomDog(): Response<DogResponse>

        @GET
        suspend fun getByBreed(@Url url: String): Response<DogResponse>
    }
}