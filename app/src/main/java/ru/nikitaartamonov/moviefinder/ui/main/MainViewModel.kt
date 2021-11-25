package ru.nikitaartamonov.moviefinder.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.nikitaartamonov.moviefinder.domain.Event

class MainViewModel : ViewModel(), MainContract.ViewModel {
    private val networkBroadcastReceiver = NetworkBroadcastReceiver()

    private val _openScreenLiveData = MutableLiveData<Event<Screens>>()
    override val openScreenLiveData = _openScreenLiveData
    private val _initStartScreenLiveData = MutableLiveData<Boolean>()
    override val initStartScreenLiveData: LiveData<Boolean> = _initStartScreenLiveData
    private val _initAndRegisterNetworkBroadcastReceiver = MutableLiveData<NetworkBroadcastReceiver>()
    override val initAndRegisterNetworkBroadcastReceiver = _initAndRegisterNetworkBroadcastReceiver

    override fun onBottomNavigationViewItemSelected(screen: Screens) {
        _openScreenLiveData.postValue(Event(screen))
    }

    override fun onViewIsReady() {
        _initStartScreenLiveData.postValue(true)
        _initAndRegisterNetworkBroadcastReceiver.postValue(networkBroadcastReceiver)
    }
}