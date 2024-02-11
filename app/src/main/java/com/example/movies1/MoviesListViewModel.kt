package com.example.movies1

import androidx.lifecycle.ViewModel
import com.example.movies1.network.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MoviesListViewModel: ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    private val repository = MoviesRepository()

    init {
        loadMovies()
    }

    private fun loadMovies() {
        _movies.value = repository.getMovies()
    }
}