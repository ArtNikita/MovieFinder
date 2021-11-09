package ru.nikitaartamonov.moviefinder.data

import android.app.Application
import ru.nikitaartamonov.moviefinder.domain.MovieEntity
import ru.nikitaartamonov.moviefinder.domain.MoviesRepo
import ru.nikitaartamonov.moviefinder.impl.MoviesRepoImpl

class App : Application() {
    lateinit var moviesRepo: MoviesRepo

    override fun onCreate() {
        super.onCreate()
        initMoviesRepo()
    }

    private fun initMoviesRepo() {
        initMoviesRepoWithTestValues()
    }

    private fun initMoviesRepoWithTestValues() {
        val tempMovieEntity = MovieEntity(
            id = "0",
            imageRes = "plug_poster_image.png",
            name = "Dude",
            description = "Awesome movie about dude.",
            year = 1998,
            imdbRating = 8.1f,
            kpRating = 7.8f,
            country = "USA, UK",
            genre = "Comedy",
            producer = "Joel Koen",
            durationInMinutes = 117
        )
        val tempMoviesList = listOf<MovieEntity>(
            tempMovieEntity, tempMovieEntity.copy(id = "1"),
            tempMovieEntity.copy(id = "2"), tempMovieEntity.copy(id = "3"),
            tempMovieEntity.copy(id = "4"), tempMovieEntity.copy(id = "5")
        )
        moviesRepo = MoviesRepoImpl(tempMoviesList)
    }
}