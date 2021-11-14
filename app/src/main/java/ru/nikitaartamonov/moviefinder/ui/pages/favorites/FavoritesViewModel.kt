package ru.nikitaartamonov.moviefinder.ui.pages.favorites

import android.util.Log
import androidx.lifecycle.ViewModel
import ru.nikitaartamonov.moviefinder.domain.MovieEntity

class FavoritesViewModel : ViewModel(), FavoritesContract.ViewModel {
    override fun onItemTouched(movieEntity: MovieEntity) {
        openMoviePage(movieEntity)
    }

    private fun openMoviePage(movieEntity: MovieEntity) {
        Log.i("@@@", movieEntity.toString())
    }
}