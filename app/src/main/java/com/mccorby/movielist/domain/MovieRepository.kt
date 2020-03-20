package com.mccorby.movielist.domain

import io.reactivex.Single

class MovieRepository(private val movieDataSource: MovieDataSource) {

    fun getMovieList(): Single<List<Movie>> {
        return movieDataSource.getMovieList()
    }
}
