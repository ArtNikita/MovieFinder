package ru.nikitaartamonov.moviefinder.ui.pages.movie_description.map

import android.content.Context
import android.content.Intent
import android.location.Geocoder
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import ru.nikitaartamonov.moviefinder.R
import ru.nikitaartamonov.moviefinder.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private val viewModel by viewModels<MapsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideStatusBar()
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        viewModel.onActivityIsReady(intent, Geocoder(this))
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.fillMapLiveData.observe(this) { event ->
            event.getContentIfNotHandled()?.let {
                it.forEach { markerOptions ->
                    mMap.addMarker(markerOptions)
                }
                mMap.animateCamera(CameraUpdateFactory.newLatLng(it.first().position))
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        viewModel.onMapIsReady()
    }

    private fun hideStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    companion object {
        fun launch(context: Context, countries: ArrayList<String>) {
            val intent = Intent(context, MapsActivity::class.java)
            intent.putStringArrayListExtra(INTENT_COUNTRIES_LIST_KEY, countries)
            context.startActivity(intent)
        }

        const val INTENT_COUNTRIES_LIST_KEY = "INTENT_COUNTRIES_LIST_KEY"
    }
}