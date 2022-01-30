package ru.nikitaartamonov.moviefinder.data

import ru.nikitaartamonov.moviefinder.BuildConfig
import ru.nikitaartamonov.moviefinder.domain.MoviesLoaderContract
import java.net.URL

class ApiConstants {
    companion object {
        const val API_KEY: String = BuildConfig.MOVIE_DB_API_KEY
        const val POSTER_URI_START = "https://image.tmdb.org/t/p/w1280/"
        const val BASE_URL = "https://api.themoviedb.org/"
        const val DEFAULT_PAGE_NUMBER = 1
        const val POPULAR_STRING = "popular"
        const val NOW_PLAYING_STRING = "now_playing"
        const val TOP_RATED_STRING = "top_rated"
        const val UPCOMING_STRING = "upcoming"

        fun getMoviesListURL(moviesType: MoviesLoaderContract.MoviesType): URL {
            val uri = when (moviesType) {
                MoviesLoaderContract.MoviesType.POPULAR -> "${BASE_URL}3/movie/popular"
                MoviesLoaderContract.MoviesType.NOW_PLAYING -> "${BASE_URL}3/movie/now_playing"
                MoviesLoaderContract.MoviesType.TOP_RATED -> "${BASE_URL}3/movie/top_rated"
                MoviesLoaderContract.MoviesType.UPCOMING -> "${BASE_URL}3/movie/upcoming"
            }
            return URL("${uri}?api_key=${API_KEY}&page=1")
        }

        fun getMoviesTypeString(moviesType: MoviesLoaderContract.MoviesType): String =
            when (moviesType) {
                MoviesLoaderContract.MoviesType.POPULAR -> POPULAR_STRING
                MoviesLoaderContract.MoviesType.NOW_PLAYING -> NOW_PLAYING_STRING
                MoviesLoaderContract.MoviesType.TOP_RATED -> TOP_RATED_STRING
                MoviesLoaderContract.MoviesType.UPCOMING -> UPCOMING_STRING
            }

        fun getMoviesTypeByString(moviesType: String): MoviesLoaderContract.MoviesType =
            when (moviesType) {
                NOW_PLAYING_STRING -> MoviesLoaderContract.MoviesType.NOW_PLAYING
                TOP_RATED_STRING -> MoviesLoaderContract.MoviesType.TOP_RATED
                UPCOMING_STRING -> MoviesLoaderContract.MoviesType.UPCOMING
                else -> MoviesLoaderContract.MoviesType.POPULAR
            }
    }
}