package ru.nikitaartamonov.moviefinder.ui.pages.movies

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.nikitaartamonov.moviefinder.R
import ru.nikitaartamonov.moviefinder.databinding.FragmentMoviesBinding

class MoviesFragment : Fragment(R.layout.fragment_movies) {
    private val binding: FragmentMoviesBinding by viewBinding(FragmentMoviesBinding::bind)
}