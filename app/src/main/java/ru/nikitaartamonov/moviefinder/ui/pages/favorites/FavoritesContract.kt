package ru.nikitaartamonov.moviefinder.ui.pages.favorites

import ru.nikitaartamonov.moviefinder.domain.MovieEntity

class FavoritesContract {

    interface ViewModel {
        fun onItemTouched(movieEntity: MovieEntity)
    }

    interface OnMovieItemClickListener {
        fun onClick(movieEntity: MovieEntity)
    }
}