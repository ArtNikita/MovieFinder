package ru.nikitaartamonov.moviefinder.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PreviewMovieEntity(
    val id: Long,
    val title: String,
    val poster_path: String,
    val release_date: String,
    val vote_average: Float
) : Parcelable