package ru.nikitaartamonov.moviefinder.data

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import ru.nikitaartamonov.moviefinder.domain.MoviesRepoRoom
import ru.nikitaartamonov.moviefinder.domain.PreviewMovieEntity
import ru.nikitaartamonov.moviefinder.impl.MoviesRepoRoomImpl
import kotlin.random.Random

private const val SHARED_PREFERENCES_NAME = "SHARED_PREFERENCES_NAME"

class App : Application() {
    val SHARED_PREFERENCES_MOVIES_TYPE_KEY = "SHARED_PREFERENCES_MOVIES_TYPE_KEY"
    val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences(
            SHARED_PREFERENCES_NAME,
            MODE_PRIVATE
        )
    }
    val favoritesMoviesRepo: MoviesRepoRoom by lazy { MoviesRepoRoomImpl(applicationContext) }

    override fun onCreate() {
        super.onCreate()
        initFavoritesMoviesRepo()
    }

    private fun initFavoritesMoviesRepo() {
        initFavoritesMoviesRepoWithTestValues()
    }

    private fun initFavoritesMoviesRepoWithTestValues() {
        val tempMovieEntity = PreviewMovieEntity(
            id = Random.nextLong(),
            posterPath = "5DpmtMBXXNDujIuSlKW3WLKuqEd.jpg",
            title = "The big Lebowski",
            releaseDate = "1998",
            voteAverage = 8.1f
        )
        favoritesMoviesRepo.addMovie(tempMovieEntity)
    }
}

val Context.app: App
    get() = applicationContext as App