package com.example.dankjokes

import retrofit2.http.GET

const val BASE_URL = "https://v2.jokeapi.dev"

interface ApiRequest {
    @GET("/joke/Miscellaneous,Dark,Pun,Spooky?blacklistFlags=nsfw,religious,political,racist,sexist,explicit")
    suspend fun getRandomJoke(): ApiData
}