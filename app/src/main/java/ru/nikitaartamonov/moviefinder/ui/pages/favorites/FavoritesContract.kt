package ru.nikitaartamonov.moviefinder.ui.pages.favorites

import androidx.lifecycle.LiveData
import ru.nikitaartamonov.moviefinder.domain.Event
import ru.nikitaartamonov.moviefinder.domain.PreviewMovieEntity

class FavoritesContract {

    interface ViewModel {
        val openMovieDescriptionLiveData: LiveData<Event<PreviewMovieEntity>>
        fun onItemTouched(movieEntity: PreviewMovieEntity)
    }
}