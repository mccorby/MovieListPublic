package com.mccorby.movielist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mccorby.movielist.domain.GetMoviesUseCase
import com.mccorby.movielist.domain.Movie
import io.reactivex.disposables.CompositeDisposable

// TODO How to inject dependencies in an view model?
class MovieListViewModel(
    private val moviesUseCase: GetMoviesUseCase,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {

    private val composite = CompositeDisposable()

    // https://proandroiddev.com/when-to-load-data-in-viewmodels-ad9616940da7
    val movieList: LiveData<List<Movie>> by lazy {
        // TODO We want this to be private as our vm should only expose immutable values
        val internalMovieList = MutableLiveData<List<Movie>>()
        val stream = moviesUseCase.execute()
            .doOnSuccess { internalMovieList.postValue(it) }
            .subscribeOn(schedulerProvider.getIoScheduler())
            .observeOn(schedulerProvider.getMainScheduler())
            // TODO Need to add `subscribe()` to make it disposable
            .subscribe()

        composite.add(stream)
        return@lazy internalMovieList
    }

    override fun onCleared() {
        super.onCleared()
        composite.dispose()
    }
}