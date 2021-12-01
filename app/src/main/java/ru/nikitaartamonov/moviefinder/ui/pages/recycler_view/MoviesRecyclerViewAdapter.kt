package ru.nikitaartamonov.moviefinder.ui.pages.recycler_view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nikitaartamonov.moviefinder.domain.PreviewMovieEntity

class MoviesRecyclerViewAdapter : RecyclerView.Adapter<MovieItemViewHolder>() {
    private lateinit var moviesList: List<PreviewMovieEntity>
    lateinit var listener: OnMovieItemClickListener

    fun setData(moviesList: List<PreviewMovieEntity>) {
        this.moviesList = moviesList
        notifyDataSetChanged()
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