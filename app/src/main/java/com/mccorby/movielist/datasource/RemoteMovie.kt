package com.mccorby.movielist.datasource

import com.mccorby.movielist.domain.Movie
import kotlinx.serialization.Serializable

@Serializable
data class RemoteMoviesResponse(val data: List<RemoteMovie>)

@Serializable
data class RemoteMovie(val id: String)

fun RemoteMovie.toDomain(): Movie {
    with(this) {
        return Movie(
            this.id
        )
    }
}

fun List<RemoteMovie>.toDomain(): List<Movie> {
    return this.map {
        it.toDomain()
    }
}