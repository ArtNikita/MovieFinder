package ru.nikitaartamonov.moviefinder.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.nikitaartamonov.moviefinder.R
import ru.nikitaartamonov.moviefinder.databinding.ActivityMainBinding
import ru.nikitaartamonov.moviefinder.ui.pages.favorites.FavoritesFragment
import ru.nikitaartamonov.moviefinder.ui.pages.movies.MoviesFragment
import ru.nikitaartamonov.moviefinder.ui.pages.settings.SettingsFragment

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = lastCustomNonConfigurationInstance as MainContract.Presenter? ?: MainPresenter()
        presenter.attach(this)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.movies_menu -> {
                    presenter.onMoviesMenuSelected()
                    true
                }
                R.id.favourites_menu -> {
                    presenter.onFavoritesMenuSelected()
                    true
                }
                R.id.settings_menu -> {
                    presenter.onSettingsMenuSelected()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun openMoviesScreen() {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentContainerFrameLayout.id, MoviesFragment())
            .commit()
    }

    override fun openFavoritesScreen() {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentContainerFrameLayout.id, FavoritesFragment())
            .commit()
    }

    override fun openSettingsScreen() {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentContainerFrameLayout.id, SettingsFragment())
            .commit()
    }

    override fun onRetainCustomNonConfigurationInstance(): Any {
        return presenter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }
}