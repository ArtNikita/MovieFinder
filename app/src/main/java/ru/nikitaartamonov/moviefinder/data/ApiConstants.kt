package ru.nikitaartamonov.moviefinder.data

import ru.nikitaartamonov.moviefinder.domain.MoviesContract
import java.net.URL

class ApiConstants {
    companion object {
        const val API_KEY: String = "0f798718b00910d0a11e67966b508e93"

        fun getMoviesListURL(movieType: MoviesContract.MovieType): URL {
            var uri = when (movieType) {
                MoviesContract.MovieType.POPULAR -> "https://api.themoviedb.org/3/movie/popular"
                MoviesContract.MovieType.NOW_PLAYING -> "https://api.themoviedb.org/3/movie/now_playing"
                MoviesContract.MovieType.TOP_RATED -> "https://api.themoviedb.org/3/movie/top_rated"
                MoviesContract.MovieType.UPCOMING -> "https://api.themoviedb.org/3/movie/upcoming"
            }
            return URL("${uri}?api_key=${API_KEY}&page=1")
        }
    }
}