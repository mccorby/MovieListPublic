package com.mccorby.movielist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mccorby.movielist.domain.GetMoviesUseCase

class MovieListViewModelFactory(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val schedulerProvider: SchedulerProvider
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieListViewModel(getMoviesUseCase, schedulerProvider) as T
    }
}