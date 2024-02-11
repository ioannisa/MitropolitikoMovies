package com.example.movies1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies1.model.MoviesRepository
import com.example.movies1.network.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoviesViewModel: ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    private val repository = MoviesRepository()

    init {
        loadMovies()
    }

    private fun loadDummyMovies() {
        _movies.value = repository.getDummyMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            _movies.value = repository.getMovies()
        }
    }
}