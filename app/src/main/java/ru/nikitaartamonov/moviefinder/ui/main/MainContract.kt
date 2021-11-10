package ru.nikitaartamonov.moviefinder.ui.main

import androidx.lifecycle.LiveData

class MainContract {

    interface ViewModel {
        val openMoviesScreenLiveData: LiveData<Boolean>
        val openFavoritesScreenLiveData: LiveData<Boolean>
        val openSettingsScreenLiveData: LiveData<Boolean>
        val initStartScreenLiveData: LiveData<Boolean>

        fun onMoviesMenuSelected()
        fun onFavoritesMenuSelected()
        fun onSettingsMenuSelected()
        fun onViewIsReady()
    }
}