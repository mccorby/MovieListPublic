package com.mccorby.movielist.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mccorby.movielist.domain.GetMoviesUseCase
import com.mccorby.movielist.domain.Movie
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class MovieListViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieListObserver: Observer<List<Movie>>

    @Mock
    private lateinit var schedulerProvider: SchedulerProvider

//    // TODO If we try to run this without a rule we get a Method getMainLooper in android.os.Looper not mocked
//    // In order to use setValue in LiveData in unit tests we need to add
//    @Rule
//    // The @Rule 'rule' must be public.
//    @JvmField
//    var rule: TestRule = InstantTaskExecutorRule()
//
//    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
//
//    @Before
//    fun setUp() {
//        Dispatchers.setMain(mainThreadSurrogate)
//    }
//
//    @After
//    fun tearDown() {
//        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
//        mainThreadSurrogate.close()
//    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        whenever(schedulerProvider.getIoScheduler()).thenReturn(Schedulers.trampoline())
        whenever(schedulerProvider.getMainScheduler()).thenReturn(Schedulers.trampoline())
    }

    @Test
    fun `Given the start loading function is invoked then the view model publishes a list of movies`() {
        val expected = Array(3) { i -> Movie(i.toString()) }.toList()
        val getMoviesUseCase = mock<GetMoviesUseCase> {
            on { execute() } doReturn Single.just(expected)
        }

        val cut = MovieListViewModel(getMoviesUseCase, schedulerProvider)
        cut.movieList.observeForever(movieListObserver)

        cut.loadMovieList()

        assert(cut.movieList.value == expected)
    }
}