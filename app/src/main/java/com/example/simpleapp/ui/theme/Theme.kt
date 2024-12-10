package com.example.simpleapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Esquema de cores para o tema escuro
private val DarkColorScheme = darkColorScheme(
    primary = RedGrey40, // Vermelho escuro para elementos principais
    secondary = Red40, // Vermelho para destaques
    background = Black40, // Preto absoluto para o fundo
    surface = Black40, // Fundo de superfícies
    onPrimary = Black40, // Texto sobre elementos primários
    onSecondary = Red80, // Texto sobre elementos secundários
    onBackground = Red80, // Texto sobre fundo preto
    onSurface = Red80 // Texto sobre superfícies escuras
)

// Esquema de cores para o tema claro
private val LightColorScheme = lightColorScheme(
    primary = Red40, // Vermelho para elementos principais
    secondary = Red80, // Vermelho claro para destaques
    background = Black80, // Preto acinzentado para fundo
    surface = RedGrey80, // Fundo de superfícies claras
    onPrimary = Black40, // Texto sobre elementos primários
    onSecondary = Black40, // Texto sobre botões secundários
    onBackground = Black40, // Texto sobre fundo claro
    onSurface = Black40 // Texto sobre superfícies claras
)

@Composable
fun SimpleAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Define se o tema escuro está ativo
    dynamicColor: Boolean = false, // Força o uso de cores personalizadas
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb() // Define a cor da barra de status
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
