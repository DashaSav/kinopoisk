package dev.shinyautumn.kinopoisk.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import dev.shinyautumn.kinopoisk.data.Country
import dev.shinyautumn.kinopoisk.data.Genre
import dev.shinyautumn.kinopoisk.data.Movie
import dev.shinyautumn.kinopoisk.ui.theme.KinopoiskTheme

@Composable
fun DetailScreen(movie: Movie) {
    Column(Modifier.fillMaxSize()) {
        ImagePreview(
            movie = movie,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun ImagePreview(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        AsyncImage(
            model = movie.posterUrlPreview,
            contentDescription = null,
            modifier = Modifier.matchParentSize()
        )
        IconButton({}) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.TopStart)
            )
            movie.nameOriginal?.let {
                Text(
                    text = it,
                    color = Color.White
                )
            }
            Text(text = movie.ratingKinopoisk.toString())
        }
    }
}

@Preview
@Composable
private fun DetailScreen_Preview() {
    KinopoiskTheme {
        DetailScreen(
            Movie(
                "Title",
                "1994",
                listOf(Country("Russia")),
                listOf(Genre("Боевик")),
                12.0,
                ""
            )
        )
    }
}
