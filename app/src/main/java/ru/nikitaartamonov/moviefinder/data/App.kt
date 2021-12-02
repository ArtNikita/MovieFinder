package ru.nikitaartamonov.moviefinder.data

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import ru.nikitaartamonov.moviefinder.domain.MoviesRepoRoom
import ru.nikitaartamonov.moviefinder.domain.PreviewMovieEntity
import ru.nikitaartamonov.moviefinder.impl.MoviesRepoRoomImpl
import kotlin.random.Random

private const val SHARED_PREFERENCES_NAME = "SHARED_PREFERENCES_NAME"

const val SHARED_PREFERENCES_MOVIES_TYPE_KEY = "SHARED_PREFERENCES_MOVIES_TYPE_KEY"

class App : Application() {
    val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences(
            SHARED_PREFERENCES_NAME,
            MODE_PRIVATE
        )
    }
    val favoritesMoviesRepo: MoviesRepoRoom by lazy { MoviesRepoRoomImpl(applicationContext) }
}

val Context.app: App
    get() = applicationContext as App