package ru.nikitaartamonov.moviefinder.ui.pages.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nikitaartamonov.moviefinder.databinding.RecyclerViewMovieItemBinding
import ru.nikitaartamonov.moviefinder.domain.MovieEntity

class MovieItemViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(
        RecyclerViewMovieItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).root
    ) {

    fun bind(movieEntity: MovieEntity) {
        //todo
    }
}