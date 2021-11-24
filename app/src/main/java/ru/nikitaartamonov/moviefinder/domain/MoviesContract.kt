package ru.nikitaartamonov.moviefinder.domain

class MoviesContract {
    enum class MoviesType {
        POPULAR, NOW_PLAYING, UPCOMING, TOP_RATED
    }

    interface ServerMoviesLoader {
        fun loadMoviesAsync(moviesType: MoviesType, callback: (MoviesRepo?) -> Unit)
    }
}