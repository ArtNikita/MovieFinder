package ru.nikitaartamonov.moviefinder.data

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import ru.nikitaartamonov.moviefinder.domain.MoviesRepo
import ru.nikitaartamonov.moviefinder.domain.PreviewMovieEntity
import ru.nikitaartamonov.moviefinder.impl.MoviesRepoImpl

private const val SHARED_PREFERENCES_NAME = "SHARED_PREFERENCES_NAME"

class App : Application() {
    val SHARED_PREFERENCES_MOVIES_TYPE_KEY = "SHARED_PREFERENCES_MOVIES_TYPE_KEY"
    val sharedPreferences: SharedPreferences by lazy { getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE) }
    lateinit var favoritesMoviesRepo: MoviesRepo

    override fun onCreate() {
        super.onCreate()
        initFavoritesMoviesRepo()
    }

    private fun initFavoritesMoviesRepo() {
        initFavoritesMoviesRepoWithTestValues()
    }

    private fun initFavoritesMoviesRepoWithTestValues() {
        val tempMovieEntity = PreviewMovieEntity(
            id = 0,
            posterPath = "5DpmtMBXXNDujIuSlKW3WLKuqEd.jpg",
            title = "The big Lebowski",
            releaseDate = "1998",
            voteAverage = 8.1f
        )
        val tempMoviesList = listOf<PreviewMovieEntity>(
            tempMovieEntity, tempMovieEntity.copy(id = 1),
            tempMovieEntity.copy(id = 2), tempMovieEntity.copy(id = 3),
            tempMovieEntity.copy(id = 4), tempMovieEntity.copy(id = 5)
        )
        favoritesMoviesRepo = MoviesRepoImpl(tempMoviesList)
    }
}

val Context.app: App
    get() = applicationContext as App