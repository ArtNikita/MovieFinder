package ru.nikitaartamonov.moviefinder.data.retrofit

import com.google.gson.annotations.SerializedName

data class MovieCreditsWrapper(
    @SerializedName("id")
    val id: Long,
    @SerializedName("cast")
    val cast: List<CastEntity>
)