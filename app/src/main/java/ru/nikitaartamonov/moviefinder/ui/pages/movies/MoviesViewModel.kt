package ru.nikitaartamonov.moviefinder.ui.pages.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.nikitaartamonov.moviefinder.domain.Event
import ru.nikitaartamonov.moviefinder.domain.MoviesContract.MoviesType
import ru.nikitaartamonov.moviefinder.domain.MoviesRepo
import ru.nikitaartamonov.moviefinder.impl.ServerMoviesLoaderImpl

class MoviesViewModel : ViewModel(), MoviesContract.ViewModel {
    private val serverMoviesLoader = ServerMoviesLoaderImpl()
    private var movies: MoviesRepo? = null

    private val _moviesLoadedLiveData = MutableLiveData<MoviesRepo>()
    override val moviesLoadedLiveData = _moviesLoadedLiveData
    private val _showDownloadErrorLiveData = MutableLiveData<Event<Boolean>>()
    override val showDownloadErrorLiveData = _showDownloadErrorLiveData


    override fun onViewIsReady(moviesType: MoviesType) {
        if (movies == null) {
            loadMovies(moviesType)
        } else {
            movies?.let {
                _moviesLoadedLiveData.postValue(it)
            }
        }
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