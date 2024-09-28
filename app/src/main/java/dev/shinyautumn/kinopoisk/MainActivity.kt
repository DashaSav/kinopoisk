package dev.shinyautumn.kinopoisk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.shinyautumn.kinopoisk.data.Movie
import dev.shinyautumn.kinopoisk.data.MoviesApi
import dev.shinyautumn.kinopoisk.data.RetrofitHelper
import dev.shinyautumn.kinopoisk.ui.screens.MoviesScreen
import dev.shinyautumn.kinopoisk.ui.theme.KinopoiskTheme

class MainActivity : ComponentActivity() {
    private val retrofit by lazy { RetrofitHelper.getInstance() }

    private val api by lazy { retrofit.create(MoviesApi::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KinopoiskTheme {
                var movies by remember { mutableStateOf(listOf<Movie>()) }

                LaunchedEffect(this) {
                    movies = getMovies()
                }

                MoviesScreen(movies)
            }
        }
    }

    private suspend fun getMovies(): List<Movie> {
        return api.getMovies().items
    }
}
