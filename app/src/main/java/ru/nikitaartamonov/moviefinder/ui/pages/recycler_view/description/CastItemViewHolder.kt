package ru.nikitaartamonov.moviefinder.ui.pages.recycler_view.description

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.nikitaartamonov.moviefinder.data.ApiConstants
import ru.nikitaartamonov.moviefinder.data.retrofit.CastEntity
import ru.nikitaartamonov.moviefinder.databinding.RecyclerViewMovieItemBinding

class CastItemViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(
        RecyclerViewMovieItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).root
    ) {

    private var binding: RecyclerViewMovieItemBinding = RecyclerViewMovieItemBinding.bind(itemView)

    fun bind(castEntity: CastEntity) {
        val firstLineText = "${castEntity.name} (${castEntity.knownForDepartment})"
        binding.movieItemNameTextView.text = firstLineText
        binding.movieItemDescriptionTextView.text = castEntity.character
        Glide
            .with(itemView.context)
            .load("${ApiConstants.POSTER_URI_START}${castEntity.profilePath}")
            .into(binding.movieItemImageView)
    }
}