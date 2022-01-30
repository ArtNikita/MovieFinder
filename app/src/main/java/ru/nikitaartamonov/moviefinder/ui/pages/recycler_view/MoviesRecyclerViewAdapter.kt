package ru.nikitaartamonov.moviefinder.ui.pages.recycler_view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nikitaartamonov.moviefinder.domain.PreviewMovieEntity

class MoviesRecyclerViewAdapter : RecyclerView.Adapter<MovieItemViewHolder>() {
    private var moviesList: List<PreviewMovieEntity> = emptyList()
    lateinit var listener: OnMovieItemClickListener

    val listSize = moviesList.size

    fun setDataAndNotify(moviesList: List<PreviewMovieEntity>) {
        this.moviesList = moviesList
        notifyDataSetChanged()
    }

    fun setData(moviesList: List<PreviewMovieEntity>) {
        this.moviesList = moviesList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        return MovieItemViewHolder(parent, listener)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = moviesList.size

    private fun getItem(position: Int) = moviesList[position]
}