package com.mccorby.movielist.datasource

import com.mccorby.movielist.domain.Movie
import com.mccorby.movielist.domain.MovieDataSource
import io.reactivex.Single

class RemoteMovieDataSource(private val api: MoviesApi) : MovieDataSource {

    override fun getMovieList(): Single<List<Movie>> {
        return api.getMovies()
            .map {
                it.data.toDomain()
            }
    }
}