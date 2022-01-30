package ru.nikitaartamonov.moviefinder.domain

interface MoviesRepo {
    val moviesList: List<PreviewMovieEntity>
    val size: Int
    fun addMovie(movieEntity: PreviewMovieEntity)
    fun deleteMovie(id: Long): Boolean
    fun getMovie(id: Long): PreviewMovieEntity?
}