package ru.nikitaartamonov.moviefinder.data

import ru.nikitaartamonov.moviefinder.domain.MoviesContract
import java.net.URL

class ApiConstants {
    companion object {
        private const val API_KEY: String = "0f798718b00910d0a11e67966b508e93"
        const val POSTER_URI_START = "https://image.tmdb.org/t/p/w1280/"

        fun getMoviesListURL(moviesType: MoviesContract.MoviesType): URL {
            val uri = when (moviesType) {
                MoviesContract.MoviesType.POPULAR -> "https://api.themoviedb.org/3/movie/popular"
                MoviesContract.MoviesType.NOW_PLAYING -> "https://api.themoviedb.org/3/movie/now_playing"
                MoviesContract.MoviesType.TOP_RATED -> "https://api.themoviedb.org/3/movie/top_rated"
                MoviesContract.MoviesType.UPCOMING -> "https://api.themoviedb.org/3/movie/upcoming"
            }
            return URL("${uri}?api_key=${API_KEY}&page=1")
        }
    }
}