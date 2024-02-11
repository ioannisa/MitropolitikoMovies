package com.example.movies1.model

import android.util.Log
import com.example.movies1.TAG
import com.example.movies1.network.Movie
import com.example.movies1.network.MoviesService

class MoviesRepository {

    suspend fun getMovies(): List<Movie> {
        val response = try {
            MoviesService.api.getMovies()
        } catch (e: Exception) {
            Log.e(TAG,"Exception with message ${e.message}")
            return emptyList()
        }

        if (response.isSuccessful && response.body() != null) {
            return response.body()!!.movies
        }

        return emptyList()
    }
}