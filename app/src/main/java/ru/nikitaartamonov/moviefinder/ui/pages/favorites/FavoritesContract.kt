package ru.nikitaartamonov.moviefinder.ui.pages.favorites

import androidx.lifecycle.LiveData
import ru.nikitaartamonov.moviefinder.domain.Event
import ru.nikitaartamonov.moviefinder.domain.PreviewMovieEntity

class FavoritesContract {

    interface ViewModel {
        val openMovieDescriptionLiveData: LiveData<Event<PreviewMovieEntity>>
        val deleteMovieByPositionLiveData: LiveData<Event<Int>>
        val notifyMovieEntityWasDeletedLiveData: LiveData<Event<PreviewMovieEntity>>
        fun onItemTouched(movieEntity: PreviewMovieEntity)
        fun onItemLongTouched(movieEntity: PreviewMovieEntity, position: Int)
    }
}