package ru.nikitaartamonov.moviefinder.ui.pages.movie_description

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.nikitaartamonov.moviefinder.databinding.ActivityMovieDescriptionBinding

class MovieDescriptionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}