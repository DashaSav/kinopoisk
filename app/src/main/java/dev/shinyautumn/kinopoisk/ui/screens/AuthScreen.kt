package dev.shinyautumn.kinopoisk.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.shinyautumn.kinopoisk.ui.theme.KinopoiskTheme

@Composable
fun AuthScreen(
    onLogin: (login: String, password: String) -> Unit,
) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val focusManger = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Text(
            text = "KinoPoisk",
            fontSize = 50.sp,
            color = Color.Cyan,
        )
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                value = login,
                onValueChange = { login = it },
                textStyle = TextStyle(
                    color = Color.White
                ),
                singleLine = true,
                placeholder = {
                    Text(text = "Логин", color = Color.Gray)
                },
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = { password = it },
                singleLine = true,
                placeholder = {
                    Text(text = "Пароль", color = Color.Gray)
                },
                textStyle = TextStyle(
                    color = Color.White
                ),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        onLogin(login, password)
                        focusManger.clearFocus()
                    }
                )
            )
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.small,
            onClick = { onLogin(login, password) },
            colors = ButtonColors(
                contentColor = Color.White,
                containerColor = Color.Cyan,
                disabledContentColor = Color.White,
                disabledContainerColor = Color.Cyan,
            )
        ) {
            Text(text = "Войти", fontSize = 20.sp)
        }
    }
}

@Preview
@Composable
private fun AuthScreen_Preview() {
    KinopoiskTheme {
        AuthScreen { _, _ -> }
    }
}
