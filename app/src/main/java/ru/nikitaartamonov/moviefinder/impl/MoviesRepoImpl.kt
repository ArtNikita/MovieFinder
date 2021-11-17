package ru.nikitaartamonov.moviefinder.impl

import ru.nikitaartamonov.moviefinder.domain.MoviesRepo
import ru.nikitaartamonov.moviefinder.domain.PreviewMovieEntity

class MoviesRepoImpl(
    inputMoviesList: List<PreviewMovieEntity> = mutableListOf()
) : MoviesRepo {
    override val moviesList: MutableList<PreviewMovieEntity> =
        inputMoviesList.toMutableList()

    override val size: Int
        get() = moviesList.size

    override fun addMovie(movieEntity: PreviewMovieEntity) {
        if (movieEntity !in moviesList) moviesList.add(movieEntity)
    }

    override fun deleteMovie(id: Long): Boolean {
        val tempSize = moviesList.size
        moviesList.remove(getMovie(id))
        return tempSize != moviesList.size
    }

    override fun getMovie(id: Long): PreviewMovieEntity? = moviesList.find { it.id == id }
}