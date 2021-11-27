package ru.nikitaartamonov.moviefinder.ui.pages.movies

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import ru.nikitaartamonov.moviefinder.R
import ru.nikitaartamonov.moviefinder.databinding.FragmentMoviesBinding
import ru.nikitaartamonov.moviefinder.domain.MoviesLoaderContract.MoviesType
import ru.nikitaartamonov.moviefinder.domain.MoviesRepo
import ru.nikitaartamonov.moviefinder.domain.PreviewMovieEntity
import ru.nikitaartamonov.moviefinder.impl.MoviesRepoImpl
import ru.nikitaartamonov.moviefinder.ui.pages.recycler_view.MoviesRecyclerViewAdapter
import ru.nikitaartamonov.moviefinder.ui.pages.recycler_view.OnMovieItemClickListener
import ru.nikitaartamonov.moviefinder.util.MyAnalytics

class MoviesFragment : Fragment(R.layout.fragment_movies) {
    private val binding: FragmentMoviesBinding by viewBinding(FragmentMoviesBinding::bind)
    private val viewModel: MoviesContract.ViewModel by viewModels<MoviesViewModel>()
    private val adapter = MoviesRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyAnalytics.logEvent(requireContext(), "MoviesFragment onCreate()")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MyAnalytics.logEvent(requireContext(), "MoviesFragment onViewCreated()")
        initMoviesTypeButton()
        initRecyclerView()
        initViewModel()
        viewModel.onViewIsReady()
    }

    private fun initMoviesTypeButton() {
        binding.moviesTypeButton.setOnClickListener {
            viewModel.onMoviesTypeButtonPressed()
        }
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
                MyAnalytics.logEvent(requireContext(), "MoviesFragment $movieEntity clicked")
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
        viewModel.showMoviesTypeMenuLiveData.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                showMoviesTypeMenu()
            }
        }
    }

    private fun showMoviesTypeMenu() {
        val popupMoviesTypeMenu = PopupMenu(requireContext(), binding.moviesTypeButton)
        popupMoviesTypeMenu.inflate(R.menu.movies_type_menu)
        popupMoviesTypeMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.popular_movies_menu -> {
                    viewModel.onMoviesTypeMenuButtonPressed(stringToMoviesType(getString(R.string.popular_movies_title)))
                    true
                }
                R.id.now_playing_movies_menu -> {
                    viewModel.onMoviesTypeMenuButtonPressed(stringToMoviesType(getString(R.string.now_playing_movies_title)))
                    true
                }
                R.id.upcoming_movies_menu -> {
                    viewModel.onMoviesTypeMenuButtonPressed(stringToMoviesType(getString(R.string.upcoming_movies_title)))
                    true
                }
                R.id.top_rated_movies_menu -> {
                    viewModel.onMoviesTypeMenuButtonPressed(stringToMoviesType(getString(R.string.top_rated_movies_title)))
                    true
                }
                else -> false
            }
        }
        popupMoviesTypeMenu.show()
    }

    private fun moviesTypeToString(moviesType: MoviesType?): String = when (moviesType) {
        MoviesType.POPULAR -> getString(R.string.popular_movies_title)
        MoviesType.NOW_PLAYING -> getString(R.string.now_playing_movies_title)
        MoviesType.UPCOMING -> getString(R.string.upcoming_movies_title)
        MoviesType.TOP_RATED -> getString(R.string.top_rated_movies_title)
        else -> throw IllegalStateException("No such movies type")
    }

    private fun stringToMoviesType(moviesTypeString: String): MoviesType = when (moviesTypeString) {
        getString(R.string.popular_movies_title) -> MoviesType.POPULAR
        getString(R.string.now_playing_movies_title) -> MoviesType.NOW_PLAYING
        getString(R.string.upcoming_movies_title) -> MoviesType.UPCOMING
        getString(R.string.top_rated_movies_title) -> MoviesType.TOP_RATED
        else -> throw IllegalStateException("No such movies type name")
    }

    private fun showDownloadError() {
        Snackbar
                .make(
                        binding.moviesFragmentRecyclerView,
                        getString(R.string.download_error_message),
                        Snackbar.LENGTH_SHORT
                ).setAction(getString(R.string.retry)) {
                    viewModel.onDownloadErrorSnackbarRetryButtonPressed()
                }
                .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        MyAnalytics.logEvent(requireContext(), "MoviesFragment onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        MyAnalytics.logEvent(requireContext(), "MoviesFragment onDestroy()")
    }

    companion object {
        private const val VERTICAL_RECYCLER_VIEW_COLUMNS_COUNT = 2
        private const val HORIZONTAL_RECYCLER_VIEW_COLUMNS_COUNT = 5
    }
}