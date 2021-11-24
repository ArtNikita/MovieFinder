package ru.nikitaartamonov.moviefinder.ui.pages.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.nikitaartamonov.moviefinder.R
import ru.nikitaartamonov.moviefinder.data.ApiConstants
import ru.nikitaartamonov.moviefinder.databinding.RecyclerViewMovieItemBinding
import ru.nikitaartamonov.moviefinder.domain.PreviewMovieEntity

class MovieItemViewHolder(parent: ViewGroup, listener: OnMovieItemClickListener) :
    RecyclerView.ViewHolder(
        RecyclerViewMovieItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).root
    ) {

    private var binding: RecyclerViewMovieItemBinding = RecyclerViewMovieItemBinding.bind(itemView)
    private lateinit var movieEntity: PreviewMovieEntity

    init {
        itemView.setOnClickListener {
            listener.onClick(movieEntity)
        }
    }

    fun bind(movieEntity: PreviewMovieEntity) {
        this.movieEntity = movieEntity
        binding.movieItemNameTextView.text = movieEntity.title
        val shortDescription = "${movieEntity.release_date}       ${movieEntity.vote_average}"
        binding.movieItemDescriptionTextView.text = shortDescription
        Glide
            .with(itemView.context)
            .load("${ApiConstants.POSTER_URI_START}${movieEntity.poster_path}")
            .into(binding.movieItemImageView)
        binding.movieItemImageView.setBackgroundResource(R.drawable.empty_rectangle)
    }
}