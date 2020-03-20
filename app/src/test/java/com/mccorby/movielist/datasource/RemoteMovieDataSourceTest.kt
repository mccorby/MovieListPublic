package com.mccorby.movielist.datasource

import com.mccorby.movielist.domain.Movie
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import org.junit.Test

class RemoteMovieDataSourceTest {

    @Test
    fun `Given a list of movies is requested the data source returns it from the API`() {
        val expected = listOf(Movie("1"), Movie("2"))
        val apiResult = RemoteMoviesResponse(listOf(RemoteMovie("1"), RemoteMovie("2")))

        val api = mock<MoviesApi> {
            on {getMovies()} doReturn Single.just(apiResult)
        }

        val cut = RemoteMovieDataSource(api)

        val result = cut.getMovieList().test()

        result.assertComplete()
            .assertNoErrors()
            .assertValues(expected)
    }
}