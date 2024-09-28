package dev.shinyautumn.kinopoisk.data

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val nameOriginal: String?,
    val year: String,
    val countries: List<Country>,
    val genres: List<Genre>,
    val ratingKinopoisk: Double,
    val posterUrlPreview: String
)

@Serializable
data class Country(val country: String)

@Serializable
data class Genre(val genre: String)
