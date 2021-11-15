package ru.nikitaartamonov.moviefinder.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieEntity(
    val id: String,
    val imageRes: String,
    val name: String,
    val description: String,
    val year: Int,
    val imdbRating: Float,
    val kpRating: Float,
    val country: String,
    val genre: String,
    val producer: String,
    val durationInMinutes: Int
): Parcelable