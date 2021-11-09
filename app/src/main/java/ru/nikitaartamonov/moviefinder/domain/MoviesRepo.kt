package ru.nikitaartamonov.moviefinder.domain

interface MoviesRepo {
    val moviesSet: Set<MovieEntity>
    fun addMovie(movieEntity: MovieEntity)
    fun deleteMovie(id: String): Boolean
    fun getMovie(id: String): MovieEntity?
}