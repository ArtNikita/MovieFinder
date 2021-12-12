package ru.nikitaartamonov.moviefinder.ui.pages.movies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.nikitaartamonov.moviefinder.data.ApiConstants
import ru.nikitaartamonov.moviefinder.data.App
import ru.nikitaartamonov.moviefinder.data.SHARED_PREFERENCES_MOVIES_TYPE_KEY
import ru.nikitaartamonov.moviefinder.data.retrofit.ServerMoviesLoaderRetrofit
import ru.nikitaartamonov.moviefinder.domain.Event
import ru.nikitaartamonov.moviefinder.domain.MoviesLoaderContract
import ru.nikitaartamonov.moviefinder.domain.MoviesLoaderContract.MoviesType
import ru.nikitaartamonov.moviefinder.domain.MoviesRepo
import ru.nikitaartamonov.moviefinder.domain.PreviewMovieEntity

class MoviesViewModel(application: Application) : AndroidViewModel(application),
    MoviesContract.ViewModel {
    private val app = application as App
    private val serverMoviesLoader: MoviesLoaderContract.ServerMoviesLoader =
        ServerMoviesLoaderRetrofit()
    private var movies: MoviesRepo? = null
    private var currentMoviesType = ApiConstants.getMoviesTypeByString(
        app.sharedPreferences.getString(
            SHARED_PREFERENCES_MOVIES_TYPE_KEY, ApiConstants.POPULAR_STRING
        ) ?: ApiConstants.POPULAR_STRING
    )

    private val _moviesLoadedLiveData = MutableLiveData<MoviesRepo>()
    override val moviesLoadedLiveData: LiveData<MoviesRepo> = _moviesLoadedLiveData
    private val _showDownloadErrorLiveData = MutableLiveData<Event<Boolean>>()
    override val showDownloadErrorLiveData: LiveData<Event<Boolean>> = _showDownloadErrorLiveData
    private val _changeMoviesButtonTextLiveData = MutableLiveData<MoviesType>()
    override val changeMoviesButtonTextLiveData: LiveData<MoviesType> =
        _changeMoviesButtonTextLiveData
    private val _showMoviesTypeMenuLiveData = MutableLiveData<Event<Boolean>>()
    override val showMoviesTypeMenuLiveData: LiveData<Event<Boolean>> = _showMoviesTypeMenuLiveData
    private val _notifyMovieAddedToFavoritesLiveData = MutableLiveData<Event<String>>()
    override val notifyMovieAddedToFavoritesLiveData: LiveData<Event<String>> =
        _notifyMovieAddedToFavoritesLiveData


    override fun onViewIsReady() {
        if (movies == null) {
            _changeMoviesButtonTextLiveData.postValue(currentMoviesType)
            loadMovies(currentMoviesType)
        } else {
            movies?.let {
                _changeMoviesButtonTextLiveData.postValue(currentMoviesType)
                _moviesLoadedLiveData.postValue(it)
            }
        }
    }

    override fun onMoviesTypeButtonPressed() {
        _showMoviesTypeMenuLiveData.postValue(Event(true))
    }

    override fun onMoviesTypeMenuButtonPressed(moviesType: MoviesType) {
        currentMoviesType = moviesType
        _changeMoviesButtonTextLiveData.postValue(moviesType)
        loadMovies(moviesType)
        app.sharedPreferences.edit().putString(
            SHARED_PREFERENCES_MOVIES_TYPE_KEY,
            ApiConstants.getMoviesTypeString(moviesType)
        ).apply()
    }

    override fun onDownloadErrorSnackbarRetryButtonPressed() {
        loadMovies(currentMoviesType)
    }

    override fun onMovieItemLongTouched(movieEntity: PreviewMovieEntity) {
        app.vibrate()
        addMovieEntityToFavorites(movieEntity)
    }

    private fun addMovieEntityToFavorites(movieEntity: PreviewMovieEntity) {
        app.favoritesMoviesRepo.addMovie(movieEntity)
        _notifyMovieAddedToFavoritesLiveData.postValue(Event(movieEntity.title))
    }

    private fun loadMovies(moviesType: MoviesType) {
        serverMoviesLoader.loadMoviesAsync(moviesType) {
            if (it == null) {
                _showDownloadErrorLiveData.postValue(Event(true))
            } else {
                movies = it
                movies?.let { moviesRepo ->
                    _moviesLoadedLiveData.postValue(moviesRepo)
                }
            }
        }
    }
}