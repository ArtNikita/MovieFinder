package ru.nikitaartamonov.moviefinder.ui.pages.movies

import androidx.lifecycle.LiveData
import ru.nikitaartamonov.moviefinder.domain.MoviesContract
import ru.nikitaartamonov.moviefinder.domain.MoviesRepo

class MoviesContract {

    interface ViewModel {
        val moviesLoadedLiveData: LiveData<MoviesRepo>

        fun onViewIsReady(moviesType: MoviesContract.MoviesType)
    }
}