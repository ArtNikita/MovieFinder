package ru.nikitaartamonov.moviefinder.impl

import ru.nikitaartamonov.moviefinder.domain.MovieEntity
import ru.nikitaartamonov.moviefinder.domain.MoviesRepo

class MoviesRepoImpl(
    inputMoviesList: List<MovieEntity> = mutableListOf()
) : MoviesRepo {
    override val moviesList: MutableList<MovieEntity> = inputMoviesList.toMutableList()

    override val size: Int
        get() = moviesList.size

    override fun addMovie(movieEntity: MovieEntity) {
        if (movieEntity !in moviesList) moviesList.add(movieEntity)
    }

    override fun deleteMovie(id: String): Boolean {
        val tempSize = moviesList.size
        moviesList.remove(getMovie(id))
        return tempSize != moviesList.size
    }

    override fun getMovie(id: String): MovieEntity? = moviesList.find { it.id == id }
}