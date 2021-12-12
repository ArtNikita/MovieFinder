package ru.nikitaartamonov.moviefinder.data.retrofit

import com.google.gson.annotations.SerializedName

data class DetailedMovieEntity(
    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("budget")
    val budget: Long,

    @SerializedName("genres")
    val genres: List<GenreWrapper>,

    @SerializedName("id")
    val id: Long,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanyWrapper>,

    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountryWrapper>,

    @SerializedName("revenue")
    val revenue: Long,

    @SerializedName("runtime")
    val runtime: Int,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("vote_average")
    val voteAverage: Float,

    @SerializedName("vote_count")
    val voteCount: Long
){
    data class GenreWrapper(
        @SerializedName("id")
        val id: Long,
        @SerializedName("name")
        val name: String)
    data class ProductionCompanyWrapper(
        @SerializedName("name")
        val name: String,
        @SerializedName("origin_country")
        val originCountry: String
    )
    data class ProductionCountryWrapper(
        @SerializedName("iso_3166_1")
        val shortName: String,
        @SerializedName("name")
        val name: String
    )
}