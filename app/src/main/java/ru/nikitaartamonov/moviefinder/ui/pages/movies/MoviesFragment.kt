package ru.nikitaartamonov.moviefinder.ui.pages.movies

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import ru.nikitaartamonov.moviefinder.R
import ru.nikitaartamonov.moviefinder.databinding.FragmentMoviesBinding
import ru.nikitaartamonov.moviefinder.domain.MoviesContract.MoviesType
import ru.nikitaartamonov.moviefinder.domain.MoviesRepo
import ru.nikitaartamonov.moviefinder.domain.PreviewMovieEntity
import ru.nikitaartamonov.moviefinder.impl.MoviesRepoImpl
import ru.nikitaartamonov.moviefinder.ui.pages.recycler_view.MoviesRecyclerViewAdapter
import ru.nikitaartamonov.moviefinder.ui.pages.recycler_view.OnMovieItemClickListener

class MoviesFragment : Fragment(R.layout.fragment_movies) {
    private val binding: FragmentMoviesBinding by viewBinding(FragmentMoviesBinding::bind)
    private val viewModel: MoviesContract.ViewModel by viewModels<MoviesViewModel>()
    private val adapter = MoviesRecyclerViewAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initViewModel()
        viewModel.onViewIsReady()
    }

    private fun initRecyclerView() {
        binding.moviesFragmentRecyclerView.layoutManager =
            GridLayoutManager(
                context,
                when (resources.configuration.orientation) {
                    Configuration.ORIENTATION_PORTRAIT -> VERTICAL_RECYCLER_VIEW_COLUMNS_COUNT
                    else -> HORIZONTAL_RECYCLER_VIEW_COLUMNS_COUNT
                }
            )
        adapter.listener = object : OnMovieItemClickListener {
            override fun onClick(movieEntity: PreviewMovieEntity) {
                //todo
            }
        }
        binding.moviesFragmentRecyclerView.adapter = adapter
        adapter.setData(MoviesRepoImpl())
    }

    private fun setDataToAdapter(moviesRepo: MoviesRepo) {
        adapter.setData(moviesRepo)
    }

    private fun initViewModel() {
        viewModel.moviesLoadedLiveData.observe(viewLifecycleOwner) {
            setDataToAdapter(it)
        }
        viewModel.showDownloadErrorLiveData.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                if (it) showDownloadError()
            }
        }
        viewModel.changeMoviesButtonTextLiveData.observe(viewLifecycleOwner) {
            binding.moviesTypeButton.text = moviesTypeToString(it)
        }
    }

    private fun moviesTypeToString(moviesType: MoviesType?): String = when (moviesType) {
        MoviesType.POPULAR -> getString(R.string.popular_movies_title)
        MoviesType.NOW_PLAYING -> getString(R.string.now_playing_movies_title)
        MoviesType.UPCOMING -> getString(R.string.upcoming_movies_title)
        MoviesType.TOP_RATED -> getString(R.string.top_rated_movies_title)
        else -> throw IllegalStateException("No such movies type")
    }

    private fun showDownloadError() {
        Snackbar
            .make(
                binding.moviesFragmentRecyclerView,
                getString(R.string.download_error_message),
                Snackbar.LENGTH_SHORT
            ).setAction(getString(R.string.retry)) {
                //todo
            }
            .show()
    }

    companion object {
        private const val VERTICAL_RECYCLER_VIEW_COLUMNS_COUNT = 2
        private const val HORIZONTAL_RECYCLER_VIEW_COLUMNS_COUNT = 5
    }
}