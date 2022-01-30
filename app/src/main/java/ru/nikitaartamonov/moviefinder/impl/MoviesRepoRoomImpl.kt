package ru.nikitaartamonov.moviefinder.impl

import android.content.Context
import android.os.Handler
import android.os.HandlerThread
import androidx.room.Room
import ru.nikitaartamonov.moviefinder.data.room.MovieEntityDao
import ru.nikitaartamonov.moviefinder.data.room.MovieEntityDb
import ru.nikitaartamonov.moviefinder.domain.MoviesRepoRoom
import ru.nikitaartamonov.moviefinder.domain.PreviewMovieEntity

private const val HANDLER_THREAD_NAME = "HANDLER_THREAD_NAME"

class MoviesRepoRoomImpl(context: Context) : MoviesRepoRoom {
    private val movieEntityDao: MovieEntityDao
    private val handlerThread by lazy { HandlerThread(HANDLER_THREAD_NAME).apply { start() } }
    private val handler = Handler(handlerThread.looper)

    init {
        val db = Room.databaseBuilder(context, MovieEntityDb::class.java, "movies.db").build()
        movieEntityDao = db.moviesEntityDao()
    }

    override fun getMoviesList(callback: ((List<PreviewMovieEntity>) -> Unit)) {
        handler.post{
            callback(movieEntityDao.getMovieEntities())
        }
    }

    override fun getSize(callback: (Int) -> Unit) {
        handler.post{
            callback(movieEntityDao.getSize())
        }
    }

    override fun addMovie(movieEntity: PreviewMovieEntity) {
        handler.post{
            movieEntityDao.addMovie(movieEntity)
        }
    }

    override fun deleteMovie(id: Long, callback: (Boolean) -> Unit) {
        handler.post{
            val previousSize = movieEntityDao.getSize()
            movieEntityDao.deleteMovie(id)
            callback(previousSize != movieEntityDao.getSize())
        }
    }

    override fun getMovie(id: Long, callback: (PreviewMovieEntity?) -> Unit) {
        handler.post{
            callback(movieEntityDao.getMovie(id))
        }
    }
}
