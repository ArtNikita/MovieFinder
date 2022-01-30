package ru.nikitaartamonov.moviefinder.data.retrofit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.nikitaartamonov.moviefinder.data.ApiConstants
import ru.nikitaartamonov.moviefinder.domain.MoviesLoaderContract
import ru.nikitaartamonov.moviefinder.domain.MoviesRepo
import ru.nikitaartamonov.moviefinder.impl.MoviesRepoImpl

class ServerMoviesLoaderRetrofit : MoviesLoaderContract.ServerMoviesLoader {
    private val retrofit = Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val moviesApi = retrofit.create(MoviesApi::class.java)

    override fun loadMoviesAsync(
        moviesType: MoviesLoaderContract.MoviesType,
        callback: (MoviesRepo?) -> Unit
    ) {
        moviesApi.getMoviesList(
            ApiConstants.getMoviesTypeString(moviesType),
            ApiConstants.API_KEY,
            ApiConstants.DEFAULT_PAGE_NUMBER
        ).enqueue(object : Callback<PreviewMovieEntityWrapper> {
            override fun onResponse(
                call: Call<PreviewMovieEntityWrapper>,
                response: Response<PreviewMovieEntityWrapper>
            ) {
                if (response.isSuccessful) {
                    callback(MoviesRepoImpl(response.body()?.results ?: emptyList()))
                } else callback(null)
            }

            override fun onFailure(call: Call<PreviewMovieEntityWrapper>, t: Throwable) {
                callback(null)
            }
        })
    }

    override fun loadDetailedMovieEntityAsync(
        id: Long,
        callback: (DetailedMovieEntity?) -> Unit
    ) {
        moviesApi.getDetailedMovieEntity(
            id,
            ApiConstants.API_KEY
        ).enqueue(object : Callback<DetailedMovieEntity> {
            override fun onResponse(
                call: Call<DetailedMovieEntity>,
                response: Response<DetailedMovieEntity>
            ) {
                if (response.isSuccessful) {
                    callback(response.body())
                } else callback(null)
            }

            override fun onFailure(call: Call<DetailedMovieEntity>, t: Throwable) {
                callback(null)
            }
        })
    }

    override fun loadMovieCreditsAsync(
        id: Long,
        callback: (List<CastEntity>?) -> Unit
    ) {
        moviesApi.getCreditsForMovie(
            id,
            ApiConstants.API_KEY
        ).enqueue(object : Callback<MovieCreditsWrapper>{
            override fun onResponse(
                call: Call<MovieCreditsWrapper>,
                response: Response<MovieCreditsWrapper>
            ) {
                if (response.isSuccessful) {
                    callback(response.body()?.cast)
                } else callback(null)
            }

            override fun onFailure(call: Call<MovieCreditsWrapper>, t: Throwable) {
                callback(null)
            }
        })
    }
}