package ru.nikitaartamonov.moviefinder.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.nikitaartamonov.moviefinder.domain.PreviewMovieEntity

@Database(
    entities = [PreviewMovieEntity::class],
    version = 1
)
abstract class MovieEntityDb : RoomDatabase() {
    abstract fun moviesEntityDao(): MovieEntityDao
}