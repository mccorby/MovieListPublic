package com.mccorby.movielist.domain

import io.reactivex.Single

interface MovieDataSource {

    fun getMovieList(): Single<List<Movie>>
}

