package ru.nikitaartamonov.moviefinder.impl

import ru.nikitaartamonov.moviefinder.domain.MoviesContract
import ru.nikitaartamonov.moviefinder.domain.PreviewMovieEntity

class ServerMovieLoaderImpl : MoviesContract.ServerMovieLoader {
    override fun loadMoviesAsync(
        movieType: MoviesContract.MovieType,
        callback: (List<PreviewMovieEntity>) -> Unit
    ) {
        TODO("Not yet implemented")
    }
}