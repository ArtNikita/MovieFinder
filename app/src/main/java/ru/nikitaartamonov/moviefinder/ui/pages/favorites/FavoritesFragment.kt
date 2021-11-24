package ru.nikitaartamonov.moviefinder.ui.pages.favorites

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.nikitaartamonov.moviefinder.R
import ru.nikitaartamonov.moviefinder.data.app
import ru.nikitaartamonov.moviefinder.databinding.FragmentFavoritesBinding
import ru.nikitaartamonov.moviefinder.domain.PreviewMovieEntity
import ru.nikitaartamonov.moviefinder.ui.pages.movie_description.MovieDescriptionActivity
import ru.nikitaartamonov.moviefinder.ui.pages.recycler_view.MoviesRecyclerViewAdapter
import ru.nikitaartamonov.moviefinder.ui.pages.recycler_view.OnMovieItemClickListener

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {
    private val binding: FragmentFavoritesBinding by viewBinding(FragmentFavoritesBinding::bind)
    private val viewModel: FavoritesContract.ViewModel by viewModels<FavoritesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.openMovieDescriptionLiveData.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { movieEntity ->
                openMovieDescription(movieEntity)
            }
        }
    }

    private fun openMovieDescription(previewMovieEntity: PreviewMovieEntity) {
        MovieDescriptionActivity.launch(requireContext(), previewMovieEntity)
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
        val adapter = MoviesRecyclerViewAdapter()
        adapter.listener = object : OnMovieItemClickListener {
            override fun onClick(movieEntity: PreviewMovieEntity) {
                viewModel.onItemTouched(movieEntity)
            }
        }
        binding.favoritesFragmentRecyclerView.adapter = adapter
        adapter.setData(requireActivity().app.favoritesMoviesRepo)
    }

    companion object {
        private const val VERTICAL_RECYCLER_VIEW_COLUMNS_COUNT = 2
        private const val HORIZONTAL_RECYCLER_VIEW_COLUMNS_COUNT = 5
    }
}