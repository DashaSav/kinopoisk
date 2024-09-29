package dev.shinyautumn.kinopoisk.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import dev.shinyautumn.kinopoisk.data.Movie
import dev.shinyautumn.kinopoisk.data.MovieDetails
import dev.shinyautumn.kinopoisk.ui.screens.AuthScreen
import dev.shinyautumn.kinopoisk.ui.screens.DetailScreen
import dev.shinyautumn.kinopoisk.ui.screens.MoviesScreen
import kotlinx.serialization.Serializable

@Serializable
object Auth

@Serializable
object Movies

@Serializable
data class Detail(val id: Int)

@Composable
fun CreateNavHost(
    navController: NavHostController,
    onLogin: (String, String) -> Unit,
    onSelectMovie: (Movie) -> Unit,
    fetchDetails: suspend (Int) -> MovieDetails,
    movies: List<Movie>
) {
    NavHost(navController = navController, startDestination = Auth) {
        composable<Auth> { AuthScreen(onLogin) }

        composable<Movies> {
            MoviesScreen(movies) { onSelectMovie(it) }
        }

        composable<Detail> { backStackEntry ->
            val detail: Detail = backStackEntry.toRoute()

            var movie by remember { mutableStateOf<MovieDetails?>(null) }
            LaunchedEffect(Unit) {
                movie = fetchDetails(detail.id)
            }

            DetailScreen(movie) { navController.popBackStack() }
        }
    }
}

