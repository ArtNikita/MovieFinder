package ru.nikitaartamonov.moviefinder.data

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Vibrator
import ru.nikitaartamonov.moviefinder.domain.MoviesRepoRoom
import ru.nikitaartamonov.moviefinder.impl.MoviesRepoRoomImpl

const val SHARED_PREFERENCES_MOVIES_TYPE_KEY = "SHARED_PREFERENCES_MOVIES_TYPE_KEY"

private const val SHARED_PREFERENCES_NAME = "SHARED_PREFERENCES_NAME"
private const val VIBRATION_DURATION: Long = 20

class App : Application() {
    val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences(
            SHARED_PREFERENCES_NAME,
            MODE_PRIVATE
        )
    }
    val favoritesMoviesRepo: MoviesRepoRoom by lazy { MoviesRepoRoomImpl(applicationContext) }

    private val vibrator: Vibrator by lazy { getSystemService(Context.VIBRATOR_SERVICE) as Vibrator }
    fun vibrate() {
        vibrator.vibrate(VIBRATION_DURATION)
    }
}

val Context.app: App
    get() = applicationContext as App