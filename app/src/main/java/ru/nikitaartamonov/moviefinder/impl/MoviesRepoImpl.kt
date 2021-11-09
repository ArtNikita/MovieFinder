package ru.nikitaartamonov.moviefinder.impl

import ru.nikitaartamonov.moviefinder.domain.MovieEntity
import ru.nikitaartamonov.moviefinder.domain.MoviesRepo

class MoviesRepoImpl(
    inputMoviesList: List<MovieEntity> = mutableListOf()
) : MoviesRepo {
    override val moviesSet: MutableSet<MovieEntity> = inputMoviesList.toMutableSet()

    override fun addMovie(movieEntity: MovieEntity) {
        moviesSet.add(movieEntity)
    }

    override fun deleteMovie(id: String): Boolean {
        val tempSize = moviesSet.size
        moviesSet.remove(getMovie(id))
        return tempSize != moviesSet.size
    }

    override fun getMovie(id: String): MovieEntity? = moviesSet.find { it.id == id }
}