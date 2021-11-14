package ru.nikitaartamonov.moviefinder.ui.pages.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.nikitaartamonov.moviefinder.domain.MovieEntity

class FavoritesViewModel : ViewModel(), FavoritesContract.ViewModel {
    private val _openMovieDescriptionLiveData = MutableLiveData<MovieEntity>()
    override val openMovieDescriptionLiveData: LiveData<MovieEntity> = _openMovieDescriptionLiveData

    override fun onItemTouched(movieEntity: MovieEntity) {
        openMoviePage(movieEntity)
    }

    private fun openMoviePage(movieEntity: MovieEntity) {
        _openMovieDescriptionLiveData.postValue(movieEntity)
    }
}