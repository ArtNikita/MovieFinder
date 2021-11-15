package ru.nikitaartamonov.moviefinder.domain

interface MoviesRepo {
    val moviesList: List<MovieEntity>
    val size: Int
    fun addMovie(movieEntity: MovieEntity)
    fun deleteMovie(id: String): Boolean
    fun getMovie(id: String): MovieEntity?
}