package com.mccorby.movielist.domain

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class GetMoviesUseCaseTest {

    @Test
    fun `Given the use case is executed it returns a list of movies`() {
        val expected = listOf(Movie("3"), Movie("4"))

        val repository = mock<MovieRepository> {
            on { getMovieList() } doReturn Single.just(expected)
        }

        val cut = GetMoviesUseCase(repository)
        val result = cut.execute().test()

        result.assertNoErrors()
            .assertComplete()
            .assertValues(expected)
    }
}