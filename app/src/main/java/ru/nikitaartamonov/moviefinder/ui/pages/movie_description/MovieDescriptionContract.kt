package ru.nikitaartamonov.moviefinder.ui.pages.movie_description

import android.content.Intent
import androidx.lifecycle.LiveData
import ru.nikitaartamonov.moviefinder.data.retrofit.CastEntity
import ru.nikitaartamonov.moviefinder.data.retrofit.DetailedMovieEntity
import ru.nikitaartamonov.moviefinder.domain.Event

class MovieDescriptionContract {
    interface ViewModel {
        fun onActivityIsReady(inputIntent: Intent)
        fun internetProblemsSnackbarRetryButtonPressed()
        fun likeToggleButtonPressed(state: Boolean)
        fun productionCountriesClicked()

        val showInternetProblemsLiveData: LiveData<Event<Boolean>>
        val setDetailedMovieValuesLiveData: LiveData<DetailedMovieEntity>
        val setMovieCastDataValuesLiveData: LiveData<List<CastEntity>>
        val movieIsFavoriteLiveData: LiveData<Boolean>
        val openMapLiveData: LiveData<Event<ArrayList<String>>>
    }

    companion object {
        const val MOVIE_ENTITY_INTENT_KEY = "MOVIE_ENTITY_INTENT_KEY"
        const val MOVIE_ENTITY_IS_FAVORITE_INTENT_KEY = "MOVIE_ENTITY_IS_FAVORITE_INTENT_KEY"
    }
}