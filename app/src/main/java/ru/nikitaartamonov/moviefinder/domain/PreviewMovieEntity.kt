package ru.nikitaartamonov.moviefinder.domain

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

const val MOVIE_ENTITY_TABLE_NAME = "MOVIE_ENTITY_TABLE_NAME"
const val ID_COLUMN_NAME = "id"

@Parcelize
@Entity(tableName = MOVIE_ENTITY_TABLE_NAME)
data class PreviewMovieEntity(
    @SerializedName("id")
    @PrimaryKey
    @ColumnInfo(name = ID_COLUMN_NAME)
    val id: Long,

    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String,

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    val posterPath: String,

    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    val releaseDate: String,

    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    val voteAverage: Float
) : Parcelable