package br.com.nbagames.designsystem.color

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

object DarkColors {

    fun createScheme(): ColorScheme = darkColorScheme(
        primary = Color(0xFF401b6d),
        onPrimary = Color(0xFFdab9ff),
        primaryContainer = Color(0xFF583486),
        onPrimaryContainer = Color(0xFFefdbff),
        secondary = Color(0xFFcfc2da),
        onSecondary = Color(0xFF352d40),
        secondaryContainer = Color(0xFF4c4357),
        onSecondaryContainer = Color(0xFFebddf6),
        tertiary = Color(0xFFf2b8c1),
        onTertiary = Color(0xFF4b252d),
        tertiaryContainer = Color(0xFF653b43),
        onTertiaryContainer = Color(0xFFffd9df),
        error = Color(0xFFffb4a9),
        errorContainer = Color(0xFF930006),
        onError = Color(0xFF680003),
        onErrorContainer = Color(0xFFffdad4),
        background = Color(0xFF1d1b1e),
        onBackground = Color(0xFFe7e1e6),
        surface = Color(0xFF1d1b1e),
        onSurface = Color(0xFFe7e1e6),
        surfaceVariant = Color(0xFF4a454e),
        onSurfaceVariant = Color(0xFFccc4cf),
        outline = Color(0xFF958e99),
        inverseOnSurface = Color(0xFF1d1b1e),
        inverseSurface = Color(0xFFe7e1e6)
    )
}
