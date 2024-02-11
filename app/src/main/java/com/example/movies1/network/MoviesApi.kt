package com.example.movies1.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MoviesApi {
    @GET("movies.json")
    suspend fun getMovies(): Response<MoviesResponse>
}

object MoviesService {
    val api: MoviesApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://services.anifantakis.eu/mitropolitiko/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesApi::class.java)
    }
}