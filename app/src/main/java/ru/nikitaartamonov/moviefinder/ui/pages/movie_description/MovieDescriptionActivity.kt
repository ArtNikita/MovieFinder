package ru.nikitaartamonov.moviefinder.ui.pages.movie_description

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import ru.nikitaartamonov.moviefinder.R
import ru.nikitaartamonov.moviefinder.databinding.ActivityMovieDescriptionBinding
import ru.nikitaartamonov.moviefinder.domain.PreviewMovieEntity
import ru.nikitaartamonov.moviefinder.ui.pages.movie_description.MovieDescriptionContract.Companion.MOVIE_ENTITY_INTENT_KEY
import ru.nikitaartamonov.moviefinder.ui.pages.movie_description.MovieDescriptionContract.Companion.MOVIE_ENTITY_IS_FAVORITE_INTENT_KEY
import ru.nikitaartamonov.moviefinder.util.MyAnalytics

class MovieDescriptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDescriptionBinding
    private val viewModel: MovieDescriptionContract.ViewModel by viewModels<MovieDescriptionViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyAnalytics.logEvent(this, "MovieDescriptionActivity onCreate()")
        hideStatusBar()
        binding = ActivityMovieDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        viewModel.onActivityIsReady(intent)
    }

    private fun initViewModel() {
        viewModel.showInternetProblemsLiveData.observe(this) {
            it.getContentIfNotHandled()?.let { showInternetProblemsSnackBar() }
        }
        viewModel.setDetailedMovieValuesLiveData.observe(this) {
            //todo
        }
        viewModel.setMovieCastDataValuesLiveData.observe(this) {
            //todo
        }
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
    }
}