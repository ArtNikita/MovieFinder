package ru.nikitaartamonov.moviefinder.ui.main

import androidx.lifecycle.LiveData

enum class Screens {
    MOVIES, FAVORITES, SETTINGS
}

class MainContract {

    interface ViewModel {
        val openScreenLiveData: LiveData<Screens>
        val initStartScreenLiveData: LiveData<Boolean>

        fun onBottomNavigationViewItemSelected(screen: Screens)
        fun onViewIsReady()
    }
}