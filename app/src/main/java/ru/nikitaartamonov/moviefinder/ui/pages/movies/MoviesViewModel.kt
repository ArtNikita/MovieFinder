package ru.nikitaartamonov.moviefinder.ui.pages.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.nikitaartamonov.moviefinder.data.retrofit.ServerMoviesLoaderRetrofit
import ru.nikitaartamonov.moviefinder.domain.Event
import ru.nikitaartamonov.moviefinder.domain.MoviesLoaderContract
import ru.nikitaartamonov.moviefinder.domain.MoviesLoaderContract.MoviesType
import ru.nikitaartamonov.moviefinder.domain.MoviesRepo

class MoviesViewModel : ViewModel(), MoviesContract.ViewModel {
    private val serverMoviesLoader: MoviesLoaderContract.ServerMoviesLoader =
        ServerMoviesLoaderRetrofit()
    private var movies: MoviesRepo? = null
    private var currentMoviesType = MoviesType.POPULAR

    private val _moviesLoadedLiveData = MutableLiveData<MoviesRepo>()
    override val moviesLoadedLiveData = _moviesLoadedLiveData
    private val _showDownloadErrorLiveData = MutableLiveData<Event<Boolean>>()
    override val showDownloadErrorLiveData = _showDownloadErrorLiveData
    private val _changeMoviesButtonTextLiveData = MutableLiveData<MoviesType>()
    override val changeMoviesButtonTextLiveData = _changeMoviesButtonTextLiveData
    private val _showMoviesTypeMenuLiveData = MutableLiveData<Event<Boolean>>()
    override val showMoviesTypeMenuLiveData = _showMoviesTypeMenuLiveData


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
    }

    override fun onDownloadErrorSnackbarRetryButtonPressed() {
        loadMovies(currentMoviesType)
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