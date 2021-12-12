package ru.nikitaartamonov.moviefinder.ui.pages.movie_description

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.nikitaartamonov.moviefinder.data.retrofit.CastEntity
import ru.nikitaartamonov.moviefinder.data.retrofit.DetailedMovieEntity
import ru.nikitaartamonov.moviefinder.data.retrofit.ServerMoviesLoaderRetrofit
import ru.nikitaartamonov.moviefinder.domain.Event
import ru.nikitaartamonov.moviefinder.domain.MoviesLoaderContract
import ru.nikitaartamonov.moviefinder.domain.PreviewMovieEntity

class MovieDescriptionViewModel : ViewModel(), MovieDescriptionContract.ViewModel {
    private val serverMovieLoader: MoviesLoaderContract.ServerMoviesLoader =
        ServerMoviesLoaderRetrofit()
    private var activityJustLaunched = true
    private var movieIsFavorite = false
    private lateinit var movieEntity: PreviewMovieEntity

    private var detailedMovieLoaded = false
    private lateinit var detailedMovieEntity: DetailedMovieEntity

    private var movieCastListLoaded = false
    private lateinit var castList: List<CastEntity>

    private val _showInternetProblemsLiveData = MutableLiveData<Event<Boolean>>()
    override val showInternetProblemsLiveData: LiveData<Event<Boolean>> =
        _showInternetProblemsLiveData

    private val _setDetailedMovieValuesLiveData = MutableLiveData<DetailedMovieEntity>()
    override val setDetailedMovieValuesLiveData: LiveData<DetailedMovieEntity> =
        _setDetailedMovieValuesLiveData

    private val _setMovieCastDataValuesLiveData = MutableLiveData<List<CastEntity>>()
    override val setMovieCastDataValuesLiveData: LiveData<List<CastEntity>> =
        _setMovieCastDataValuesLiveData

    override fun onActivityIsReady(inputIntent: Intent) {
        if (activityJustLaunched) {
            activityJustLaunched = false
            initStartValues(inputIntent)
        }
        if (!detailedMovieLoaded) {
            loadDetailedMovie()
        }
        if (!movieCastListLoaded) {
            loadMovieCastList()
        }
        if (detailedMovieLoaded) {
            _setDetailedMovieValuesLiveData.postValue(detailedMovieEntity)
        }
        if (movieCastListLoaded) {
            _setMovieCastDataValuesLiveData.postValue(castList)
        }
    }

    override fun internetProblemsSnackbarRetryButtonPressed() {
        if (!detailedMovieLoaded) {
            loadDetailedMovie()
        }
        if (!movieCastListLoaded) {
            loadMovieCastList()
        }
    }

    private fun loadMovieCastList() {
        serverMovieLoader.loadMovieCreditsAsync(movieEntity.id) {
            if (it == null) {
                _showInternetProblemsLiveData.postValue(Event(true))
            } else {
                castList = it
                _setMovieCastDataValuesLiveData.postValue(castList)
                movieCastListLoaded = true
            }
        }
    }

    private fun loadDetailedMovie() {
        serverMovieLoader.loadDetailedMovieEntityAsync(movieEntity.id) {
            if (it == null) {
                _showInternetProblemsLiveData.postValue(Event(true))
            } else {
                detailedMovieEntity = it
                _setDetailedMovieValuesLiveData.postValue(detailedMovieEntity)
                detailedMovieLoaded = true
            }
        }
    }

    private fun initStartValues(inputIntent: Intent) {
        movieIsFavorite = inputIntent.getBooleanExtra(
            MovieDescriptionContract.MOVIE_ENTITY_IS_FAVORITE_INTENT_KEY, false
        )
        inputIntent.getParcelableExtra<PreviewMovieEntity>(MovieDescriptionContract.MOVIE_ENTITY_INTENT_KEY)
            ?.let { movieEntity = it }
    }
}