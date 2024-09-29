package dev.shinyautumn.kinopoisk.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import dev.shinyautumn.kinopoisk.data.Movie
import dev.shinyautumn.kinopoisk.ui.screens.AuthScreen
import dev.shinyautumn.kinopoisk.ui.screens.DetailScreen
import dev.shinyautumn.kinopoisk.ui.screens.MoviesScreen
import kotlinx.serialization.Serializable

@Serializable
object Auth

@Serializable
object Movies

@Serializable
data class Detail(val movie: Movie)

@Composable
fun CreateNavHost(
    navController: NavHostController,
    onLogin: (String, String) -> Unit,
    movies: List<Movie>
) {
    NavHost(navController = navController, startDestination = Auth) {
        composable<Auth> { AuthScreen(onLogin) }

        composable<Movies> {
            MoviesScreen(movies) { navController.navigate(Detail(it)) }
        }

        composable<Detail> { backStackEntry ->
            val detail: Detail = backStackEntry.toRoute()
            DetailScreen(detail.movie)
        }
    }
}

