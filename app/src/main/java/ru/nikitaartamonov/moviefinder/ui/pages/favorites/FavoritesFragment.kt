package ru.nikitaartamonov.moviefinder.ui.pages.favorites

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.nikitaartamonov.moviefinder.R
import ru.nikitaartamonov.moviefinder.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {
    private val binding: FragmentFavoritesBinding by viewBinding(FragmentFavoritesBinding::bind)
}