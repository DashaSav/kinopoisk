package dev.shinyautumn.kinopoisk.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
fun MovieItem(
    movie: Movie,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = movie.posterUrlPreview,
            contentDescription = null,
            modifier = Modifier.weight(0.4f)
        )

        Box(
            modifier = Modifier.weight(0.6f)) {
            Column {
                Text(
                    text = movie.nameOriginal.toString(),
                    color = Color.White
                )
                Text(
                    text = movie.genres.joinToString { it.genre },
                    color = Color.Gray
                )
                Text(
                    text = "${movie.year}, ${movie.countries[0].country}",
                    color = Color.Gray
                )
            }

            Text(
                text = movie.ratingKinopoisk.toString(),
                color = Color.Cyan,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }
}

@Preview
@Composable
private fun MovieItem_Preview() {
    KinopoiskTheme {
        MovieItem(movie = Movie(
            123,
            "Title",
            "1994",
            listOf(Country("Russia")),
            listOf(Genre("Боевик")),
            12.0,
            "",
            ""
        ))
    }
}
