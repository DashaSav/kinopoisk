package dev.shinyautumn.kinopoisk.data

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val kinopoiskId: Int,
    val nameOriginal: String? = null,
    val year: String,
    val countries: List<Country>,
    val genres: List<Genre>,
    val ratingKinopoisk: Double?,
    val posterUrlPreview: String,
    val posterUrl: String,
)

@Serializable
data class Country(val country: String)

@Serializable
data class Genre(val genre: String)
