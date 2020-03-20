package com.mccorby.movielist.datasource

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.reactivex.Single
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.GET

interface MoviesApi {

    @GET("movies")
    fun getMovies(): Single<RemoteMoviesResponse>
}

@UseExperimental(UnstableDefault::class)
fun getRetrofitApi(baseUrl: String): MoviesApi {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(
            Json(JsonConfiguration(strictMode = false))
                .asConverterFactory(contentType = MediaType.get("application/json")))
        .build()

    return retrofit.create(MoviesApi::class.java)
}