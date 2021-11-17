package ru.nikitaartamonov.moviefinder.domain

class MoviesContract {
    enum class MovieType {
        POPULAR, NOW_PLAYING, UPCOMING, TOP_RATED
    }

    interface ServerMovieLoader {
        fun loadMoviesAsync(movieType: MovieType, callback: (List<PreviewMovieEntity>) -> Unit)
    }
}