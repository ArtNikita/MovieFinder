package ru.nikitaartamonov.moviefinder.ui.pages.movie_description

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Paint
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ToggleButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import ru.nikitaartamonov.moviefinder.R
import ru.nikitaartamonov.moviefinder.data.ApiConstants
import ru.nikitaartamonov.moviefinder.data.retrofit.DetailedMovieEntity
import ru.nikitaartamonov.moviefinder.data.retrofit.DetailedMovieEntity.GenreWrapper.Companion.genresToText
import ru.nikitaartamonov.moviefinder.data.retrofit.DetailedMovieEntity.ProductionCompanyWrapper.Companion.productionCompaniesToText
import ru.nikitaartamonov.moviefinder.data.retrofit.DetailedMovieEntity.ProductionCountryWrapper.Companion.productionCountriesToText
import ru.nikitaartamonov.moviefinder.databinding.ActivityMovieDescriptionBinding
import ru.nikitaartamonov.moviefinder.domain.PreviewMovieEntity
import ru.nikitaartamonov.moviefinder.ui.pages.movie_description.MovieDescriptionContract.Companion.MOVIE_ENTITY_INTENT_KEY
import ru.nikitaartamonov.moviefinder.ui.pages.movie_description.MovieDescriptionContract.Companion.MOVIE_ENTITY_IS_FAVORITE_INTENT_KEY
import ru.nikitaartamonov.moviefinder.ui.pages.movie_description.map.MapsActivity
import ru.nikitaartamonov.moviefinder.ui.pages.recycler_view.description.CastRecyclerViewAdapter
import ru.nikitaartamonov.moviefinder.util.MyAnalytics

class MovieDescriptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDescriptionBinding
    private val viewModel: MovieDescriptionContract.ViewModel by viewModels<MovieDescriptionViewModel>()
    private lateinit var adapter: CastRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyAnalytics.logEvent(this, "MovieDescriptionActivity onCreate()")
        hideStatusBar()
        binding = ActivityMovieDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        initRecyclerView()
        initClickListeners()
        viewModel.onActivityIsReady(intent)
    }

    private fun initClickListeners() {
        binding.likeToggleButton.setOnClickListener { viewModel.likeToggleButtonPressed((it as ToggleButton).isChecked) }
        binding.productionCountriesTextView.setOnClickListener {
            viewModel.productionCountriesClicked()
        }
    }

    private fun initViewModel() {
        viewModel.showInternetProblemsLiveData.observe(this) {
            it.getContentIfNotHandled()?.let { showInternetProblemsSnackBar() }
        }
        viewModel.movieIsFavoriteLiveData.observe(this) {
            binding.likeToggleButton.isChecked = it
        }
        viewModel.setDetailedMovieValuesLiveData.observe(this) {
            setDetailedMovieValues(it)
        }
        viewModel.setMovieCastDataValuesLiveData.observe(this) {
            adapter.setDataAndNotify(it)
        }
        viewModel.openMapLiveData.observe(this) {
            it.getContentIfNotHandled()
                ?.let { countriesList -> MapsActivity.launch(this, countriesList) }
        }
    }

    private fun setDetailedMovieValues(detailedMovieEntity: DetailedMovieEntity) {
        Glide
            .with(this)
            .load("${ApiConstants.POSTER_URI_START}${detailedMovieEntity.backdropPath}")
            .into(binding.backgroundPosterImageView)
        binding.titleTextView.text = detailedMovieEntity.title
        binding.overviewTextView.text = detailedMovieEntity.overview
        binding.voteAverageTextView.text = detailedMovieEntity.voteAverage.toString()
        binding.voteCountTextView.text = detailedMovieEntity.voteCount.toString()
        binding.releaseDateTextView.text = detailedMovieEntity.releaseDate
        val runtimeText = "${detailedMovieEntity.runtime} ${getString(R.string.minutes)}"
        binding.runtimeTextView.text = runtimeText
        binding.productionCountriesTextView.text =
            productionCountriesToText(detailedMovieEntity.productionCountries)
        binding.productionCountriesTextView.paintFlags =
            binding.productionCountriesTextView.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding.genresTextView.text = genresToText(detailedMovieEntity.genres)
        val budgetText = "${detailedMovieEntity.budget}$"
        binding.budgetTextView.text = budgetText
        val revenueText = "${detailedMovieEntity.revenue}$"
        binding.revenueTextView.text = revenueText
        binding.productionCompaniesTextView.text =
            productionCompaniesToText(detailedMovieEntity.productionCompanies)
    }

    private fun initRecyclerView() {
        binding.castRecyclerView.layoutManager =
            GridLayoutManager(
                this,
                when (resources.configuration.orientation) {
                    Configuration.ORIENTATION_PORTRAIT -> VERTICAL_RECYCLER_VIEW_COLUMNS_COUNT
                    else -> HORIZONTAL_RECYCLER_VIEW_COLUMNS_COUNT
                }
            )
        adapter = CastRecyclerViewAdapter()
        binding.castRecyclerView.adapter = adapter
    }

    private fun showInternetProblemsSnackBar() {
        Snackbar.make(
            this,
            binding.backgroundPosterImageView,
            getString(R.string.internet_problems_message),
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction(getString(R.string.retry)) {
                viewModel.internetProblemsSnackbarRetryButtonPressed()
            }
            .show()
    }

    private fun hideStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        MyAnalytics.logEvent(this, "MovieDescriptionActivity onDestroy()")
    }

    companion object {
        fun launch(context: Context, movieEntity: PreviewMovieEntity, movieIsFavorite: Boolean) {
            val intent = Intent(context, MovieDescriptionActivity::class.java)
            intent.putExtra(MOVIE_ENTITY_INTENT_KEY, movieEntity)
            intent.putExtra(MOVIE_ENTITY_IS_FAVORITE_INTENT_KEY, movieIsFavorite)
            context.startActivity(intent)
        }

        private const val VERTICAL_RECYCLER_VIEW_COLUMNS_COUNT = 2
        private const val HORIZONTAL_RECYCLER_VIEW_COLUMNS_COUNT = 5

    }
}