package com.mccorby.movielist.domain

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import org.junit.Test

class MovieRepositoryTest {

    @Test
    fun `Given a request to get a list of movies then the repository returns it`() {
        val expected = listOf(Movie("3"), Movie("4"))
        val movieDataSource = mock<MovieDataSource> {
            on { getMovieList() } doReturn Single.just(listOf(Movie("3"), Movie("4")))
        }

        val cut = MovieRepository(movieDataSource)

        val result = cut.getMovieList().test()

        result.assertComplete()
            .assertNoErrors()
            .assertValues(expected)
    }
}