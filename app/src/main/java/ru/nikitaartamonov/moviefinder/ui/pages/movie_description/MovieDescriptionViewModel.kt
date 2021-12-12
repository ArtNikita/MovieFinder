package ru.nikitaartamonov.moviefinder.ui.pages.movie_description

import android.content.Intent
import androidx.lifecycle.ViewModel
import ru.nikitaartamonov.moviefinder.data.retrofit.ServerMoviesLoaderRetrofit
import ru.nikitaartamonov.moviefinder.domain.MoviesLoaderContract
import ru.nikitaartamonov.moviefinder.domain.PreviewMovieEntity

class MovieDescriptionViewModel : ViewModel(), MovieDescriptionContract.ViewModel {
    private val serverMovieLoader: MoviesLoaderContract.ServerMoviesLoader =
        ServerMoviesLoaderRetrofit()
    private var activityJustLaunched = true
    private var movieIsFavorite = false
    private lateinit var movieEntity: PreviewMovieEntity

    override fun onActivityIsReady(inputIntent: Intent) {
        if (activityJustLaunched) {
            activityJustLaunched = false
            initStartValues(inputIntent)
        }
        //todo start progress bar
        //todo load info
        //todo set info
        //todo stop progress bar
    }

    private fun initStartValues(inputIntent: Intent) {
        movieIsFavorite = inputIntent.getBooleanExtra(
            MovieDescriptionContract.MOVIE_ENTITY_IS_FAVORITE_INTENT_KEY, false
        )
        inputIntent.getParcelableExtra<PreviewMovieEntity>(MovieDescriptionContract.MOVIE_ENTITY_INTENT_KEY)
            ?.let { movieEntity = it }
    }
}