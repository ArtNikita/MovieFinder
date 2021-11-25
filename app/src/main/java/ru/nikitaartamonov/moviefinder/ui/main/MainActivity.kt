package ru.nikitaartamonov.moviefinder.ui.main

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.nikitaartamonov.moviefinder.R
import ru.nikitaartamonov.moviefinder.databinding.ActivityMainBinding
import ru.nikitaartamonov.moviefinder.ui.pages.favorites.FavoritesFragment
import ru.nikitaartamonov.moviefinder.ui.pages.movies.MoviesFragment
import ru.nikitaartamonov.moviefinder.ui.pages.settings.SettingsFragment
import ru.nikitaartamonov.moviefinder.util.MyAnalytics

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainContract.ViewModel by viewModels<MainViewModel>()

    private lateinit var networkBroadcastReceiver: NetworkBroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyAnalytics.logEvent(this, "MainActivity onCreate")
        hideStatusBar()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initOnClickListeners()
        initViewModel()
        viewModel.onViewIsReady()
    }

    private fun hideStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    private fun initOnClickListeners() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.movies_menu -> {
                    viewModel.onBottomNavigationViewItemSelected(Screens.MOVIES)
                    true
                }
                R.id.favourites_menu -> {
                    viewModel.onBottomNavigationViewItemSelected(Screens.FAVORITES)
                    true
                }
                R.id.settings_menu -> {
                    viewModel.onBottomNavigationViewItemSelected(Screens.SETTINGS)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun initViewModel() {
        viewModel.openScreenLiveData.observe(this) {
            it.getContentIfNotHandled()?.let { screen ->
                MyAnalytics.logEvent(this, "$screen nav btn clicked")
                when (screen) {
                    Screens.MOVIES -> openMoviesScreen()
                    Screens.FAVORITES -> openFavoritesScreen()
                    Screens.SETTINGS -> openSettingsScreen()
                }
            }
        }
        viewModel.initStartScreenLiveData.observe(this) {
            if (supportFragmentManager.fragments.isEmpty()) openMoviesScreen()
        }
        viewModel.initAndRegisterNetworkBroadcastReceiver.observe(this) {
            networkBroadcastReceiver = it
            registerReceiver(networkBroadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        }
    }

    private fun openMoviesScreen() {
        supportFragmentManager
                .beginTransaction()
                .replace(binding.fragmentContainerFrameLayout.id, MoviesFragment())
                .commit()
    }

    private fun openFavoritesScreen() {
        supportFragmentManager
                .beginTransaction()
                .replace(binding.fragmentContainerFrameLayout.id, FavoritesFragment())
                .commit()
    }

    private fun openSettingsScreen() {
        supportFragmentManager
                .beginTransaction()
                .replace(binding.fragmentContainerFrameLayout.id, SettingsFragment())
                .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        MyAnalytics.logEvent(this, "MainActivity onDestroy")
        unregisterReceiver(networkBroadcastReceiver)
    }
}