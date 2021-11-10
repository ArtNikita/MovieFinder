package ru.nikitaartamonov.moviefinder.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(), MainContract.ViewModel {
    private val _openMoviesScreenLiveData = MutableLiveData<Boolean>()
    override val openMoviesScreenLiveData: LiveData<Boolean> = _openMoviesScreenLiveData
    private val _openFavoritesScreenLiveData = MutableLiveData<Boolean>()
    override val openFavoritesScreenLiveData: LiveData<Boolean> = _openFavoritesScreenLiveData
    private val _openSettingsScreenLiveData = MutableLiveData<Boolean>()
    override val openSettingsScreenLiveData: LiveData<Boolean> = _openSettingsScreenLiveData
    private val _initStartScreenLiveData = MutableLiveData<Boolean>()
    override val initStartScreenLiveData: LiveData<Boolean> = _initStartScreenLiveData

    override fun onMoviesMenuSelected() {
        _openMoviesScreenLiveData.postValue(true)
    }

    override fun onFavoritesMenuSelected() {
        _openFavoritesScreenLiveData.postValue(true)
    }

    override fun onSettingsMenuSelected() {
        _openSettingsScreenLiveData.postValue(true)
    }

    override fun onViewIsReady() {
        _initStartScreenLiveData.postValue(true)
    }
}