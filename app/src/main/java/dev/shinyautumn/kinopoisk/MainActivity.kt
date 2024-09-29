package dev.shinyautumn.kinopoisk

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.shinyautumn.kinopoisk.data.Movie
import dev.shinyautumn.kinopoisk.data.MovieDetails
import dev.shinyautumn.kinopoisk.data.MoviesApi
import dev.shinyautumn.kinopoisk.data.RetrofitHelper
import dev.shinyautumn.kinopoisk.ui.navigation.CreateNavHost
import dev.shinyautumn.kinopoisk.ui.navigation.Detail
import dev.shinyautumn.kinopoisk.ui.navigation.Movies
import dev.shinyautumn.kinopoisk.ui.theme.KinopoiskTheme

class MainActivity : ComponentActivity() {
    private val logins = mapOf(
        "test" to "test",
        "dasha" to "test",
    )

    private val retrofit by lazy { RetrofitHelper.getInstance() }

    private val api by lazy { retrofit.create(MoviesApi::class.java) }

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            navController = rememberNavController()
            var movies by remember { mutableStateOf(listOf<Movie>()) }

            LaunchedEffect(this) {
                movies = getMovies()
            }

            KinopoiskTheme {
                CreateNavHost(
                    navController = navController,
                    onLogin = ::login,
                    onSelectMovie = ::onSelectMovie,
                    movies = movies,
                    fetchDetails = ::fetchDetails
                )
            }
        }
    }

    private suspend fun getMovies(): List<Movie> {
        return api.getMovies().items
    }

    private fun onSelectMovie(movie: Movie) {
        navController.navigate(Detail(movie.kinopoiskId))
    }

    private suspend fun fetchDetails(id: Int): MovieDetails {
        return api.getMovieDetails(id)
    }

    private fun login(login: String, password: String) {
        if (login in logins && logins[login] == password) {
            navController.navigate(Movies)
        } else {
            showToast("No such user or incorrect password")
        }
    }

    private fun showToast(message: String) {
        Toast
            .makeText(this, message, Toast.LENGTH_SHORT)
            .show()
    }
}
