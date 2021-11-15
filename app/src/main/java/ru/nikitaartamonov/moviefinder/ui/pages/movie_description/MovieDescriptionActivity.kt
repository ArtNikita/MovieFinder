package ru.nikitaartamonov.moviefinder.ui.pages.movie_description

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.nikitaartamonov.moviefinder.databinding.ActivityMovieDescriptionBinding
import ru.nikitaartamonov.moviefinder.domain.MovieEntity

class MovieDescriptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object {
        private const val MOVIE_ENTITY_INTENT_KEY = "MOVIE_ENTITY_INTENT_KEY"

        fun launch(context: Context, movieEntity: MovieEntity) {
            val intent = Intent(context, MovieDescriptionActivity::class.java)
            intent.putExtra(MOVIE_ENTITY_INTENT_KEY, movieEntity)
            context.startActivity(intent)
        }
    }
}