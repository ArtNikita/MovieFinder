package ru.nikitaartamonov.moviefinder.ui.main

import android.Manifest
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
        val checkAndAskForPermissionsLiveData: LiveData<Event<Array<PERMISSIONS>>>
        val requestPermissionLiveData: LiveData<Event<PERMISSIONS>>
        val showAccessLocationPermissionExplanationLiveData: LiveData<Event<Boolean>>

        fun onBottomNavigationViewItemSelected(screen: Screens)
        fun onViewIsReady()
        fun showPermissionExplanation(permission: PERMISSIONS)
        fun requestPermission(permission: PERMISSIONS)
    }

    enum class PERMISSIONS(val stringValue: String, val code: Int) {
        ACCESS_COARSE_LOCATION(Manifest.permission.ACCESS_COARSE_LOCATION, 1212)
    }
}