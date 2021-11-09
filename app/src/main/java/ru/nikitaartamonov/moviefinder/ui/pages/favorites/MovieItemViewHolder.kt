package ru.nikitaartamonov.moviefinder.ui.pages.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nikitaartamonov.moviefinder.R
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

    private var binding: RecyclerViewMovieItemBinding = RecyclerViewMovieItemBinding.bind(itemView)

    fun bind(movieEntity: MovieEntity) {
        binding.movieItemNameTextView.text = movieEntity.name
        val shortDescription = "${movieEntity.year} ${
            itemView.context.getString(R.string.imdb)
        }: ${movieEntity.imdbRating} ${
            itemView.context.getString(R.string.kinopoisk)
        }: ${movieEntity.kpRating}"
        binding.movieItemDescriptionTextView.text = shortDescription
        binding.movieItemImageView.setBackgroundResource(R.drawable.plug_poster_image)
    }
}