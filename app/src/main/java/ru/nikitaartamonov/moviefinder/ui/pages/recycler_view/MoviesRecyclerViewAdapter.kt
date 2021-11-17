package ru.nikitaartamonov.moviefinder.ui.pages.recycler_view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nikitaartamonov.moviefinder.domain.MoviesRepo
import ru.nikitaartamonov.moviefinder.impl.MoviesRepoImpl

class MoviesRecyclerViewAdapter : RecyclerView.Adapter<MovieItemViewHolder>() {
    private var moviesRepo: MoviesRepo = MoviesRepoImpl()
    lateinit var listener: OnMovieItemClickListener

    fun setData(moviesRepo: MoviesRepo) {
        this.moviesRepo = moviesRepo
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        return MovieItemViewHolder(parent, listener)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = moviesRepo.size

    private fun getItem(position: Int) = moviesRepo.moviesList[position]
}