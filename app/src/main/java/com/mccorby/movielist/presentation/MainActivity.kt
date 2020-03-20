package com.mccorby.movielist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.mccorby.movielist.R
import com.mccorby.movielist.datasource.RemoteMovieDataSource
import com.mccorby.movielist.datasource.getRetrofitApi
import com.mccorby.movielist.domain.GetMoviesUseCase
import com.mccorby.movielist.domain.Movie
import com.mccorby.movielist.domain.MovieDataSource
import com.mccorby.movielist.domain.MovieRepository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        injectMe()
    }

    private fun injectMe() {
        val moviesApi = getRetrofitApi("https://movies-sample.herokuapp.com/api/")
        val movieDataSource = RemoteMovieDataSource(moviesApi)
        val movieRepository = MovieRepository(movieDataSource)
        val getMoviesUseCase = GetMoviesUseCase(movieRepository)
        val schedulerProvider = object : SchedulerProvider {}
        viewModel = MovieListViewModelFactory(
            getMoviesUseCase,
            schedulerProvider
        ).create(MovieListViewModel::class.java)

        viewModel.movieList.observe(
            this,
            Observer { onMovieListChanged(it) }
        )
    }

    private fun onMovieListChanged(movieList: List<Movie>) {
        movieList.forEach { println(it) }
    }
}
