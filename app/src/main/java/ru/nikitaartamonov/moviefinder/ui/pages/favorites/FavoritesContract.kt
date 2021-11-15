package ru.nikitaartamonov.moviefinder.ui.pages.favorites

import androidx.lifecycle.LiveData
import ru.nikitaartamonov.moviefinder.domain.Event
import ru.nikitaartamonov.moviefinder.domain.MovieEntity

class FavoritesContract {

    interface ViewModel {
        val openMovieDescriptionLiveData: LiveData<Event<MovieEntity>>
        fun onItemTouched(movieEntity: MovieEntity)
    }

    interface OnMovieItemClickListener {
        fun onClick(movieEntity: MovieEntity)
    }
}