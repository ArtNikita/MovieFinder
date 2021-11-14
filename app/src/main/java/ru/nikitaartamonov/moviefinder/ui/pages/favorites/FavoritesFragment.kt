package ru.nikitaartamonov.moviefinder.ui.pages.favorites

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.nikitaartamonov.moviefinder.R
import ru.nikitaartamonov.moviefinder.data.App
import ru.nikitaartamonov.moviefinder.data.app
import ru.nikitaartamonov.moviefinder.databinding.FragmentFavoritesBinding
import ru.nikitaartamonov.moviefinder.domain.MovieEntity

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {
    private val binding: FragmentFavoritesBinding by viewBinding(FragmentFavoritesBinding::bind)
    private val viewModel: FavoritesContract.ViewModel by viewModels<FavoritesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.openMovieDescriptionLiveData.observe(viewLifecycleOwner){ movieEntity ->
            openMovieDescription(movieEntity)
        }
    }

    private fun openMovieDescription(movieEntity: MovieEntity) {
        TODO("Not yet implemented")
    }

    private fun initRecyclerView() {
        binding.favoritesFragmentRecyclerView.layoutManager =
            GridLayoutManager(
                context,
                when (resources.configuration.orientation) {
                    Configuration.ORIENTATION_PORTRAIT -> VERTICAL_RECYCLER_VIEW_COLUMNS_COUNT
                    else -> HORIZONTAL_RECYCLER_VIEW_COLUMNS_COUNT
                }
            )
        val adapter = FavoritesRecyclerViewAdapter()
        adapter.listener = object : FavoritesContract.OnMovieItemClickListener {
            override fun onClick(movieEntity: MovieEntity) {
                viewModel.onItemTouched(movieEntity)
            }
        }
        binding.favoritesFragmentRecyclerView.adapter = adapter
        adapter.setData(requireActivity().app.moviesRepo)
    }

    companion object {
        private const val VERTICAL_RECYCLER_VIEW_COLUMNS_COUNT = 2
        private const val HORIZONTAL_RECYCLER_VIEW_COLUMNS_COUNT = 5
    }
}