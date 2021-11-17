package ru.nikitaartamonov.moviefinder.ui.pages.movies

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.nikitaartamonov.moviefinder.R
import ru.nikitaartamonov.moviefinder.data.app
import ru.nikitaartamonov.moviefinder.databinding.FragmentMoviesBinding
import ru.nikitaartamonov.moviefinder.domain.MovieEntity
import ru.nikitaartamonov.moviefinder.ui.pages.recycler_view.MoviesRecyclerViewAdapter
import ru.nikitaartamonov.moviefinder.ui.pages.recycler_view.OnMovieItemClickListener

class MoviesFragment : Fragment(R.layout.fragment_movies) {
    private val binding: FragmentMoviesBinding by viewBinding(FragmentMoviesBinding::bind)
    private val viewModel: MoviesContract.ViewModel by viewModels<MoviesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerViews()
    }

    private fun initRecyclerViews() {
        initPopularMoviesRecyclerView()
    }

    private fun initPopularMoviesRecyclerView() {
        binding.moviesFragmentRecyclerView.layoutManager =
            GridLayoutManager(
                context,
                when (resources.configuration.orientation) {
                    Configuration.ORIENTATION_PORTRAIT -> VERTICAL_RECYCLER_VIEW_COLUMNS_COUNT
                    else -> HORIZONTAL_RECYCLER_VIEW_COLUMNS_COUNT
                }
            )
        val adapter = MoviesRecyclerViewAdapter()
        adapter.listener = object : OnMovieItemClickListener {
            override fun onClick(movieEntity: MovieEntity) {
                //todo
            }
        }
        binding.moviesFragmentRecyclerView.adapter = adapter
        adapter.setData(requireActivity().app.favoritesMoviesRepo)
    }

    companion object {
        private const val VERTICAL_RECYCLER_VIEW_COLUMNS_COUNT = 2
        private const val HORIZONTAL_RECYCLER_VIEW_COLUMNS_COUNT = 5
    }
}