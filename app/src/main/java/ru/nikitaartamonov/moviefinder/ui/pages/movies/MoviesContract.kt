package ru.nikitaartamonov.moviefinder.ui.pages.movies

import androidx.lifecycle.LiveData
import ru.nikitaartamonov.moviefinder.domain.Event
import ru.nikitaartamonov.moviefinder.domain.MoviesLoaderContract
import ru.nikitaartamonov.moviefinder.domain.MoviesRepo

class MoviesContract {

    interface ViewModel {
        val moviesLoadedLiveData: LiveData<MoviesRepo>
        val showDownloadErrorLiveData: LiveData<Event<Boolean>>
        val changeMoviesButtonTextLiveData: LiveData<MoviesLoaderContract.MoviesType>
        val showMoviesTypeMenuLiveData: LiveData<Event<Boolean>>

        fun onViewIsReady()
        fun onMoviesTypeButtonPressed()
        fun onMoviesTypeMenuButtonPressed(moviesType: MoviesLoaderContract.MoviesType)
        fun onDownloadErrorSnackbarRetryButtonPressed()
    }
}