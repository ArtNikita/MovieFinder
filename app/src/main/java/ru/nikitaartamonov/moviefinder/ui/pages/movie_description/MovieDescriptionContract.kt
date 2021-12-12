package ru.nikitaartamonov.moviefinder.ui.pages.movie_description

import android.content.Intent

class MovieDescriptionContract {
    interface ViewModel {
        fun onActivityIsReady(inputIntent: Intent)
    }

    companion object {
        const val MOVIE_ENTITY_INTENT_KEY = "MOVIE_ENTITY_INTENT_KEY"
        const val MOVIE_ENTITY_IS_FAVORITE_INTENT_KEY = "MOVIE_ENTITY_IS_FAVORITE_INTENT_KEY"
    }
}