package ru.nikitaartamonov.moviefinder.ui.main

import androidx.lifecycle.LiveData
import ru.nikitaartamonov.moviefinder.domain.Event

enum class Screens {
    MOVIES, FAVORITES, SETTINGS
}

class MainContract {

    interface ViewModel {
        val openScreenLiveData: LiveData<Event<Screens>>
        val initStartScreenLiveData: LiveData<Boolean>
        val initAndRegisterNetworkBroadcastReceiver: LiveData<NetworkBroadcastReceiver>

        fun onBottomNavigationViewItemSelected(screen: Screens)
        fun onViewIsReady()
    }
}