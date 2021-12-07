package ru.nikitaartamonov.moviefinder.ui.pages.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.nikitaartamonov.moviefinder.data.App
import ru.nikitaartamonov.moviefinder.domain.Event
import ru.nikitaartamonov.moviefinder.domain.PreviewMovieEntity

class FavoritesViewModel(application: Application) : AndroidViewModel(application), FavoritesContract.ViewModel {
    private val app = application as App
    private val _openMovieDescriptionLiveData = MutableLiveData<Event<PreviewMovieEntity>>()
    override val openMovieDescriptionLiveData: LiveData<Event<PreviewMovieEntity>> =
        _openMovieDescriptionLiveData
    private val _deleteMovieByPositionLiveData = MutableLiveData<Event<Int>>()
    override val deleteMovieByPositionLiveData = _deleteMovieByPositionLiveData
    private val _notifyMovieEntityWasDeletedLiveData = MutableLiveData<Event<PreviewMovieEntity>>()
    override val notifyMovieEntityWasDeletedLiveData = _notifyMovieEntityWasDeletedLiveData

    override fun onItemTouched(movieEntity: PreviewMovieEntity) {
        openMoviePage(movieEntity)
    }

    override fun onItemLongTouched(movieEntity: PreviewMovieEntity, position: Int) {
        app.vibrate()
        app.favoritesMoviesRepo.deleteMovie(movieEntity.id){
            if (it){
                _deleteMovieByPositionLiveData.postValue(Event(position))
                _notifyMovieEntityWasDeletedLiveData.postValue(Event(movieEntity))
            }
        }
    }

    private fun openMoviePage(movieEntity: PreviewMovieEntity) {
        _openMovieDescriptionLiveData.postValue(Event(movieEntity))
    }
}