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

    private val _selectedMovie = MutableStateFlow<Movie?>(null)
    val selectedMovie: StateFlow<Movie?> = _selectedMovie

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            _movies.value = repository.getMovies()
        }
    }

    fun selectMovie(movieId: Int) {
        _selectedMovie.value = getMovieById(movieId)
    }

    private fun getMovieById(movieId: Int): Movie? {
        return _movies.value.firstOrNull { it.id == movieId }
    }
}