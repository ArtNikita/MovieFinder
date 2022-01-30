package ru.nikitaartamonov.moviefinder.ui.pages.movie_description.map

import android.content.Intent
import android.location.Geocoder
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ru.nikitaartamonov.moviefinder.domain.Event

class MapsViewModel : ViewModel() {

    val fillMapLiveData: LiveData<Event<ArrayList<MarkerOptions>>> = MutableLiveData()

    private lateinit var countriesList: ArrayList<String>
    private var markersList = arrayListOf<MarkerOptions>()

    private var activityJustLaunched = true
    private var mapIsReady = false
    private var markersListFilled = false
        set(value) {
            field = value
            if (mapIsReady && value) fillMap()
        }

    fun onActivityIsReady(inputIntent: Intent, geocoder: Geocoder) {
        mapIsReady = false
        if (activityJustLaunched) {
            activityJustLaunched = false
            countriesList =
                inputIntent.getStringArrayListExtra(MapsActivity.INTENT_COUNTRIES_LIST_KEY)
                    ?: arrayListOf()
            fillLatLngArrayList(countriesList, geocoder)
        }
    }

    private fun fillLatLngArrayList(
        countriesList: java.util.ArrayList<String>,
        geocoder: Geocoder
    ) {
        Thread {
            countriesList.forEach { county ->
                val address = geocoder.getFromLocationName(county, 1).first()
                markersList.add(
                    MarkerOptions().
                    position(LatLng(address.latitude, address.longitude)).
                    title(county))
            }
            markersListFilled = true
        }.start()
    }

    fun onMapIsReady() {
        mapIsReady = true
        if (markersListFilled) fillMap()
    }

    private fun fillMap() {
        (fillMapLiveData as MutableLiveData).postValue(Event(markersList))
    }
}