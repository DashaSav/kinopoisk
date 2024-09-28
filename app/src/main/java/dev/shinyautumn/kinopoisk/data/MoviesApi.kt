package dev.shinyautumn.kinopoisk.data

import retrofit2.http.GET
import retrofit2.http.Headers

interface MoviesApi {

    @GET("films")
    @Headers("X-API-KEY: de1db718-950e-449d-88a1-39a41062cee6")
    suspend fun getMovies(): MoviesResponse
}
