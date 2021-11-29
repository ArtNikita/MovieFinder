package ru.nikitaartamonov.moviefinder.data

import ru.nikitaartamonov.moviefinder.domain.MoviesLoaderContract
import java.net.URL

class ApiConstants {
    companion object {
        const val API_KEY: String = "0f798718b00910d0a11e67966b508e93"
        const val POSTER_URI_START = "https://image.tmdb.org/t/p/w1280/"
        const val BASE_URL = "https://api.themoviedb.org/"
        const val DEFAULT_PAGE_NUMBER = 1

        fun getMoviesListURL(moviesType: MoviesLoaderContract.MoviesType): URL {
            val uri = when (moviesType) {
                MoviesLoaderContract.MoviesType.POPULAR -> "${BASE_URL}3/movie/popular"
                MoviesLoaderContract.MoviesType.NOW_PLAYING -> "${BASE_URL}3/movie/now_playing"
                MoviesLoaderContract.MoviesType.TOP_RATED -> "${BASE_URL}3/movie/top_rated"
                MoviesLoaderContract.MoviesType.UPCOMING -> "${BASE_URL}3/movie/upcoming"
            }
            return URL("${uri}?api_key=${API_KEY}&page=1")
        }

        fun getMoviesTypeString(moviesType: MoviesLoaderContract.MoviesType): String = when (moviesType) {
            MoviesLoaderContract.MoviesType.POPULAR -> "popular"
            MoviesLoaderContract.MoviesType.NOW_PLAYING -> "now_playing"
            MoviesLoaderContract.MoviesType.TOP_RATED -> "top_rated"
            MoviesLoaderContract.MoviesType.UPCOMING -> "upcoming"
        }
    }
}