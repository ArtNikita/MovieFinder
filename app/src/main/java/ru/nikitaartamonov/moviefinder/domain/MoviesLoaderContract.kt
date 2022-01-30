package ru.nikitaartamonov.moviefinder.domain

import ru.nikitaartamonov.moviefinder.data.retrofit.CastEntity
import ru.nikitaartamonov.moviefinder.data.retrofit.DetailedMovieEntity

class MoviesLoaderContract {
    enum class MoviesType {
        POPULAR, NOW_PLAYING, UPCOMING, TOP_RATED
    }

    interface ServerMoviesLoader {
        fun loadMoviesAsync(moviesType: MoviesType, callback: (MoviesRepo?) -> Unit)
        fun loadDetailedMovieEntityAsync(id: Long, callback: (DetailedMovieEntity?) -> Unit)
        fun loadMovieCreditsAsync(id: Long, callback: (List<CastEntity>?) -> Unit)
    }
}