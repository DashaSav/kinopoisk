package dev.shinyautumn.kinopoisk.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.shinyautumn.kinopoisk.data.Country
import dev.shinyautumn.kinopoisk.data.Genre
import dev.shinyautumn.kinopoisk.data.Movie
import dev.shinyautumn.kinopoisk.ui.components.MovieItem
import dev.shinyautumn.kinopoisk.ui.theme.KinopoiskTheme
import kotlinx.coroutines.launch

@Composable
fun MoviesScreen(
    movies: List<Movie>,
    onSearch: suspend (String) -> Unit,
    onExit: () -> Unit,
    onMovieClick: (Movie) -> Unit,
) {
    var isExpanded by remember { mutableStateOf(false) }
    val years = listOf("1994", "1995", "1996")

    Scaffold(
        modifier = Modifier
            .background(Color.Black)
            .windowInsetsPadding(WindowInsets.systemBars),
        topBar = { TopBar(onExit) }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(it)
        ) {
            item {
                Column {
                    SearchBar(onSearch)

                    DropdownMenu(
                        expanded = isExpanded,
                        onDismissRequest = { isExpanded = !isExpanded }
                    ) {
                        for (year in years) {
                            DropdownMenuItem(
                                text = {
                                    Text(text = year)
                                },
                                onClick = { /*TODO*/ }
                            )
                        }
                    }
                }
            }

            items(movies) { movie ->
                MovieItem(
                    movie = movie,
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onMovieClick(movie) }
                )
            }
        }
    }
}

@Composable
private fun TopBar(onExit: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(8.dp)
    ) {
        Text(
            text = "KinoPoisk",
            color = Color.Cyan,
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.Center)
        )
        IconButton(
            onClick = onExit,
            modifier = Modifier.align(Alignment.CenterEnd),
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                contentDescription = null,
                tint = Color.Cyan,
            )
        }
    }
}

@Composable
private fun SearchBar(onSearch: suspend (String) -> Unit) {
    var search by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.SwapVert,
                contentDescription = null,
                tint = Color.Cyan
            )
        }
        OutlinedTextField(value = search,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { search = it },
            textStyle = TextStyle(color = Color.White),
            placeholder = {
                Text(text = "keyword", color = Color.Gray)
            },
            trailingIcon = {
                IconButton(onClick = {
                    scope.launch { onSearch(search) }
                }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = Color.Cyan,
                    )
                }
            },
            keyboardActions = KeyboardActions(onSearch = { scope.launch { onSearch(search) } })
        )

    }
}

@Preview
@Composable
private fun MoviesScreen_Preview() {
    KinopoiskTheme {
        MoviesScreen(
            movies = listOf(Movie(
                123,
                "Title",
                "1994",
                listOf(Country("Russia")),
                listOf(Genre("Боевик")),
                12.0,
                "",
                ""
            )),
            onSearch = {},
            onMovieClick = { _ -> },
            onExit = {}
        )
    }
}
