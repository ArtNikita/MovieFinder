package ru.nikitaartamonov.moviefinder.ui.pages.recycler_view

import ru.nikitaartamonov.moviefinder.domain.PreviewMovieEntity

interface OnMovieItemClickListener {
    fun onClick(movieEntity: PreviewMovieEntity)
    fun onLongClick(movieEntity: PreviewMovieEntity)
}