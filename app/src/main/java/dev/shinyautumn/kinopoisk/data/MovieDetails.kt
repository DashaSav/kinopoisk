package dev.shinyautumn.kinopoisk.data

import kotlinx.serialization.Serializable

@Serializable
data class MovieDetails(
    val kinopoiskId: Int,
    val nameOriginal: String? = null,
    val posterUrl: String,
    val webUrl: String?,
    val ratingKinopoisk: Double,
    val year: String,
    val description: String?,
    val countries: List<Country>,
    val genres: List<Genre>,
)
