package ru.nikitaartamonov.moviefinder.ui.pages.movies

import androidx.lifecycle.LiveData
import ru.nikitaartamonov.moviefinder.domain.Event
import ru.nikitaartamonov.moviefinder.domain.MoviesContract
import ru.nikitaartamonov.moviefinder.domain.MoviesRepo

class MoviesContract {

    interface ViewModel {
        val moviesLoadedLiveData: LiveData<MoviesRepo>
        val showDownloadErrorLiveData: LiveData<Event<Boolean>>

        fun onViewIsReady(moviesType: MoviesContract.MoviesType)
    }
}