package ru.nikitaartamonov.moviefinder.ui.pages.favorites

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nikitaartamonov.moviefinder.domain.MoviesRepo
import ru.nikitaartamonov.moviefinder.impl.MoviesRepoImpl

class FavoritesRecyclerViewAdapter : RecyclerView.Adapter<MovieItemViewHolder>() {
    private var moviesRepo: MoviesRepo = MoviesRepoImpl()

    fun setData(moviesRepo: MoviesRepo) {
        this.moviesRepo = moviesRepo
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        return MovieItemViewHolder(parent)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = moviesRepo.size

    private fun getItem(position: Int) = moviesRepo.moviesList[position]
}