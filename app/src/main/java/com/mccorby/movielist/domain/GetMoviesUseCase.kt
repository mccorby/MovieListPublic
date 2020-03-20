package com.mccorby.movielist.domain

import io.reactivex.Single

class GetMoviesUseCase(private val repository: MovieRepository) {

    fun execute(): Single<List<Movie>> {
        return repository.getMovieList()
    }
}