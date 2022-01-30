package ru.nikitaartamonov.moviefinder.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.nikitaartamonov.moviefinder.domain.Event

class MainViewModel : ViewModel(), MainContract.ViewModel {
    private var appJustStarted = true
    private val permissionsToAsk = arrayOf(MainContract.PERMISSIONS.ACCESS_COARSE_LOCATION)
    private val networkBroadcastReceiver = NetworkBroadcastReceiver()

    private val _openScreenLiveData = MutableLiveData<Event<Screens>>()
    override val openScreenLiveData: LiveData<Event<Screens>> = _openScreenLiveData
    private val _initStartScreenLiveData = MutableLiveData<Boolean>()
    override val initStartScreenLiveData: LiveData<Boolean> = _initStartScreenLiveData
    private val _initAndRegisterNetworkBroadcastReceiver =
        MutableLiveData<NetworkBroadcastReceiver>()
    override val initAndRegisterNetworkBroadcastReceiver: LiveData<NetworkBroadcastReceiver> =
        _initAndRegisterNetworkBroadcastReceiver
    private val _checkAndAskForPermissionsLiveData =
        MutableLiveData<Event<Array<MainContract.PERMISSIONS>>>()
    override val checkAndAskForPermissionsLiveData: LiveData<Event<Array<MainContract.PERMISSIONS>>> =
        _checkAndAskForPermissionsLiveData
    private val _showAccessLocationPermissionExplanationLiveData = MutableLiveData<Event<Boolean>>()
    override val showAccessLocationPermissionExplanationLiveData: LiveData<Event<Boolean>> =
        _showAccessLocationPermissionExplanationLiveData
    private val _requestPermissionLiveData = MutableLiveData<Event<MainContract.PERMISSIONS>>()
    override val requestPermissionLiveData: LiveData<Event<MainContract.PERMISSIONS>> =
        _requestPermissionLiveData

    override fun onBottomNavigationViewItemSelected(screen: Screens) {
        _openScreenLiveData.postValue(Event(screen))
    }

    override fun onViewIsReady() {
        _initStartScreenLiveData.postValue(true)
        _initAndRegisterNetworkBroadcastReceiver.postValue(networkBroadcastReceiver)
        if (appJustStarted) {
            appJustStarted = false
            _checkAndAskForPermissionsLiveData.postValue(Event(permissionsToAsk))
        }
    }

    override fun showPermissionExplanation(permission: MainContract.PERMISSIONS) {
        when (permission) {
            MainContract.PERMISSIONS.ACCESS_COARSE_LOCATION -> {
                _showAccessLocationPermissionExplanationLiveData.postValue(Event(true))
            }
        }
    }

    override fun requestPermission(permission: MainContract.PERMISSIONS) {
        _requestPermissionLiveData.postValue(Event(permission))
    }

    override fun onPermissionExplanationDialogCanceled(permission: MainContract.PERMISSIONS) {
        requestPermission(permission)
    }
}