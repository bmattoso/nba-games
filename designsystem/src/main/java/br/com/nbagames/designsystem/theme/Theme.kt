package br.com.nbagames.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import br.com.nbagames.designsystem.R

private val DarkColorPalette = darkColors(
    primary = Color(R.color.brightGray),
    primaryVariant = Color(R.color.blackCurrant),
    secondary = Color(R.color.londonHue)
)

private val LightColorPalette = lightColors(
    primary = Color(R.color.brightGrayLight),
    primaryVariant = Color(R.color.blackCurrant),
    secondary = Color(R.color.londonHue)
)

@Composable
fun NbaGamesTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}