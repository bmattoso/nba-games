package br.com.nbagames.designsystem.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import br.com.nbagames.designsystem.color.DarkColors
import br.com.nbagames.designsystem.color.LightColors

@Composable
fun NbaGamesTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colorScheme = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && darkTheme -> dynamicDarkColorScheme(LocalContext.current)
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !darkTheme -> dynamicLightColorScheme(LocalContext.current)
        darkTheme -> DarkColors.createScheme()
        else -> LightColors.createScheme()
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}
