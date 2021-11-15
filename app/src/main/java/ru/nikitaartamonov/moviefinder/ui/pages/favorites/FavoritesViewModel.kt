package ru.nikitaartamonov.moviefinder.ui.pages.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.nikitaartamonov.moviefinder.domain.Event
import ru.nikitaartamonov.moviefinder.domain.MovieEntity

class FavoritesViewModel : ViewModel(), FavoritesContract.ViewModel {
    private val _openMovieDescriptionLiveData = MutableLiveData<Event<MovieEntity>>()
    override val openMovieDescriptionLiveData: LiveData<Event<MovieEntity>> =
        _openMovieDescriptionLiveData

    override fun onItemTouched(movieEntity: MovieEntity) {
        openMoviePage(movieEntity)
    }

    private fun openMoviePage(movieEntity: MovieEntity) {
        _openMovieDescriptionLiveData.postValue(Event(movieEntity))
    }
}