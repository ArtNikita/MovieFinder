package ru.nikitaartamonov.moviefinder.data.retrofit

import com.google.gson.annotations.SerializedName

data class CastEntity(
    @SerializedName("gender")
    val gender: Int,

    @SerializedName("known_for_department")
    val knownForDepartment: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("profile_path")
    val profilePath: String,

    @SerializedName("character")
    val character: String
)