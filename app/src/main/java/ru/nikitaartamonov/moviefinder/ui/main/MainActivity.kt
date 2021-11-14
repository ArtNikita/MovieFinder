package ru.nikitaartamonov.moviefinder.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.nikitaartamonov.moviefinder.R
import ru.nikitaartamonov.moviefinder.databinding.ActivityMainBinding
import ru.nikitaartamonov.moviefinder.domain.Screens
import ru.nikitaartamonov.moviefinder.ui.pages.favorites.FavoritesFragment
import ru.nikitaartamonov.moviefinder.ui.pages.movies.MoviesFragment
import ru.nikitaartamonov.moviefinder.ui.pages.settings.SettingsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainContract.ViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initOnClickListeners()
        initViewModel()
        viewModel.onViewIsReady()
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
            when (it) {
                Screens.MOVIES -> openMoviesScreen()
                Screens.FAVORITES -> openFavoritesScreen()
                Screens.SETTINGS -> openSettingsScreen()
                else -> throw IllegalStateException("Unknown screen launched")
            }
        }
        viewModel.initStartScreenLiveData.observe(this) {
            if (supportFragmentManager.fragments.isEmpty()) openMoviesScreen()
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
}