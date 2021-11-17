package ru.nikitaartamonov.moviefinder.ui.pages.recycler_view

import ru.nikitaartamonov.moviefinder.domain.MovieEntity

interface OnMovieItemClickListener {
    fun onClick(movieEntity: MovieEntity)
}