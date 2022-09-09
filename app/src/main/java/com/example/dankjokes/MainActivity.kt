package com.example.dankjokes

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.dankjokes.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var joke = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        makeApiRequest()

        binding.btnNext.setOnClickListener {
            makeApiRequest()
        }

        binding.btnShare.setOnClickListener {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type="text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, joke)
            startActivity(Intent.createChooser(shareIntent,"Spread Laughter"))
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("SetTextI18n")
    private fun makeApiRequest() {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequest::class.java)

        GlobalScope.launch (Dispatchers.IO){
            try {
                val response = api.getRandomJoke()

                if (response.type == "twopart") {
                    withContext(Dispatchers.Main){
                        binding.tvJoke.text = "${response.setup}\n${response.delivery}"
                    }
                    joke = "${response.setup}\n${response.delivery}"
                } else {
                    withContext(Dispatchers.Main){
                        binding.tvJoke.text = response.joke
                    }
                    joke = response.joke
                }
            } catch (e: Error) {
                Log.d("MainActivity", e.message!!)
            }
        }
    }
}