package dev.shinyautumn.kinopoisk.data

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface MoviesApi {

    @GET("films")
    @Headers("X-API-KEY: de1db718-950e-449d-88a1-39a41062cee6")
    suspend fun getMovies(): MoviesResponse

    @GET("films/{id}")
    @Headers("X-API-KEY: de1db718-950e-449d-88a1-39a41062cee6")
    suspend fun getMovieDetails(@Path("id") id: Int): MovieDetails
}
