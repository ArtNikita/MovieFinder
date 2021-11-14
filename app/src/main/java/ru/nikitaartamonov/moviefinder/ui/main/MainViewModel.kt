package ru.nikitaartamonov.moviefinder.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.nikitaartamonov.moviefinder.domain.Screens

class MainViewModel : ViewModel(), MainContract.ViewModel {
    private val _openScreenLiveData = MutableLiveData<Screens>()
    override val openScreenLiveData: LiveData<Screens> = _openScreenLiveData
    private val _initStartScreenLiveData = MutableLiveData<Boolean>()
    override val initStartScreenLiveData: LiveData<Boolean> = _initStartScreenLiveData

    override fun onBottomNavigationViewItemSelected(screen: Screens) {
        _openScreenLiveData.postValue(screen)
    }

    override fun onViewIsReady() {
        _initStartScreenLiveData.postValue(true)
    }
}