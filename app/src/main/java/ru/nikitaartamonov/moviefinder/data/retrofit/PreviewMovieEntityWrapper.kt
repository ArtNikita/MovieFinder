package ru.nikitaartamonov.moviefinder.data.retrofit

import com.google.gson.annotations.SerializedName
import ru.nikitaartamonov.moviefinder.domain.PreviewMovieEntity

data class PreviewMovieEntityWrapper(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<PreviewMovieEntity>
)