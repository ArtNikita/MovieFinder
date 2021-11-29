package ru.nikitaartamonov.moviefinder.data.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {
    @GET("3/movie/{moviesType}")
    fun getMoviesList(
        @Path("moviesType") moviesType: String,
        @Query("api_key") key: String,
        @Query("page") pageNumber: Int
    ): Call<PreviewMovieEntityWrapper>
}