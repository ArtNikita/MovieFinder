package ru.nikitaartamonov.moviefinder.ui.pages.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.nikitaartamonov.moviefinder.domain.Event
import ru.nikitaartamonov.moviefinder.domain.PreviewMovieEntity

class FavoritesViewModel : ViewModel(), FavoritesContract.ViewModel {
    private val _openMovieDescriptionLiveData = MutableLiveData<Event<PreviewMovieEntity>>()
    override val openMovieDescriptionLiveData: LiveData<Event<PreviewMovieEntity>> =
        _openMovieDescriptionLiveData

    override fun onItemTouched(movieEntity: PreviewMovieEntity) {
        openMoviePage(movieEntity)
    }

    private fun openMoviePage(movieEntity: PreviewMovieEntity) {
        _openMovieDescriptionLiveData.postValue(Event(movieEntity))
    }
}