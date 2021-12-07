package ru.nikitaartamonov.moviefinder.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import ru.nikitaartamonov.moviefinder.domain.ID_COLUMN_NAME
import ru.nikitaartamonov.moviefinder.domain.MOVIE_ENTITY_TABLE_NAME
import ru.nikitaartamonov.moviefinder.domain.PreviewMovieEntity

@Dao
interface MovieEntityDao {
    @Query("SELECT * FROM $MOVIE_ENTITY_TABLE_NAME")
    fun getMovieEntities(): List<PreviewMovieEntity>

    @Insert(onConflict = REPLACE)
    fun addMovie(movieEntity: PreviewMovieEntity)

    @Query("DELETE FROM $MOVIE_ENTITY_TABLE_NAME WHERE $ID_COLUMN_NAME=:id")
    fun deleteMovie(id: Long)

    @Query("SELECT * FROM $MOVIE_ENTITY_TABLE_NAME WHERE $ID_COLUMN_NAME=:id")
    fun getMovie(id: Long): PreviewMovieEntity?

    @Query("SELECT COUNT(*) FROM $MOVIE_ENTITY_TABLE_NAME")
    fun getSize(): Int
}