package ru.nikitaartamonov.moviefinder.impl

import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import ru.nikitaartamonov.moviefinder.data.ApiConstants
import ru.nikitaartamonov.moviefinder.domain.MoviesContract
import ru.nikitaartamonov.moviefinder.domain.MoviesRepo
import ru.nikitaartamonov.moviefinder.domain.PreviewMovieEntity
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.util.*

class ServerMoviesLoaderImpl : MoviesContract.ServerMoviesLoader {
    private val gson by lazy { Gson() }

    override fun loadMoviesAsync(
        moviesType: MoviesContract.MoviesType,
        callback: (MoviesRepo?) -> Unit
    ) {
        Thread {
            lateinit var connection: HttpURLConnection
            try {
                connection =
                    ApiConstants.getMoviesListURL(moviesType).openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connectTimeout = 5_000
                val bufferedReader = BufferedReader(InputStreamReader(connection.inputStream))
                val result = bufferedReader.readLines().toString()
                callback.invoke(getMoviesRepoFromJson(result))
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection.disconnect()
            }
        }.start()
    }

    private fun getMoviesRepoFromJson(json: String): MoviesRepo? {
        val jsonObject = JSONArray(json)
        val resultJson = gson.fromJson(
            JSONObject(jsonObject[0].toString()).get("results").toString(),
            Array<PreviewMovieEntity>::class.java
        )
        return MoviesRepoImpl(resultJson.toList())
    }
}