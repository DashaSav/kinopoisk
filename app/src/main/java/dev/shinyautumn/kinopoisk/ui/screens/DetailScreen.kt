package dev.shinyautumn.kinopoisk.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dev.shinyautumn.kinopoisk.data.Country
import dev.shinyautumn.kinopoisk.data.Genre
import dev.shinyautumn.kinopoisk.data.MovieDetails
import dev.shinyautumn.kinopoisk.ui.theme.KinopoiskTheme

@Composable
fun DetailScreen(
    movie: MovieDetails?,
    onBackClick: () -> Unit,
) {

    Column(Modifier
        .fillMaxSize()
        .background(Color.Black)
        .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        ImagePreview(
            movie = movie,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            onBackClick = onBackClick
        )
        Column(Modifier.weight(1f)) {
            Text(
                text = "Описание",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = movie?.description.toString(),
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            movie?.genres?.joinToString { it.genre }?.let {
                Text(
                    text = it,
                    color = Color.Gray,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            Text(
                text = "${movie?.year}, ${movie?.countries?.joinToString { it.country }}",
                color = Color.Gray,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
private fun ImagePreview(
    movie: MovieDetails?,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    Box(modifier) {
        AsyncImage(
            model = movie?.posterUrl,
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Fit
        )
        IconButton(onBackClick) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
            )
        }
        movie?.nameOriginal?.let {
            Text(
                text = it,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(horizontal = 16.dp)
            )
        }
        Text(
            text = movie?.ratingKinopoisk.toString(),
            color = Color.Cyan,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(horizontal = 16.dp)
        )
    }
}

@Preview
@Composable
private fun DetailScreen_Preview() {
    KinopoiskTheme {
        DetailScreen(
            MovieDetails(
                123,
                "Title",
                "1994",
                "",
                12.0,
                "1994",
                "Описаниееее",
                listOf(Country("Russia")),
                listOf(Genre("Боевик")),
            ),
            onBackClick = {}
        )
    }
}
