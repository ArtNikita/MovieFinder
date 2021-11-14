package ru.nikitaartamonov.moviefinder.ui.main

import androidx.lifecycle.LiveData
import ru.nikitaartamonov.moviefinder.domain.Screens

class MainContract {

    interface ViewModel {
        val openScreenLiveData: LiveData<Screens>
        val initStartScreenLiveData: LiveData<Boolean>

        fun onBottomNavigationViewItemSelected(screen: Screens)
        fun onViewIsReady()
    }
}