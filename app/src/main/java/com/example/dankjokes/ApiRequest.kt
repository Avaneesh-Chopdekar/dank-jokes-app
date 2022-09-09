package com.example.dankjokes

import androidx.annotation.Keep
import retrofit2.http.GET

const val BASE_URL = "https://v2.jokeapi.dev"

@Keep
interface ApiRequest {
    @GET("/joke/Miscellaneous,Dark,Pun,Spooky?blacklistFlags=nsfw,religious,political,racist,sexist,explicit")
    suspend fun getRandomJoke(): ApiData
}