package dev.shinyautumn.kinopoisk.data

import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
    val total: Int,
    val totalPages: Int,
    val items: List<Movie>,
)
