package br.com.nbagames.designsystem.color

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

object LightColors {

    val primary = Color(0XFF714DA0)
    val onPrimary = Color(0XFFFFFFFF)
    val primaryContainer = Color(0XFFEFDBFF)
    val onPrimaryContainer = Color(0XFF290056)
    val secondary = Color(0XFF645A6F)
    val onSecondary = Color(0XFFFFFFFF)
    val secondaryContainer = Color(0XFFEBDDF6)
    val onSecondaryContainer = Color(0XFF20182A)
    val tertiary = Color(0XFF7F515A)
    val onTertiary = Color(0XFFFFFFFF)
    val tertiaryContainer = Color(0XFFFFD9DF)
    val onTertiaryContainer = Color(0XFF321018)
    val error = Color(0XFFBA1B1B)
    val errorContainer = Color(0XFFFFDAD4)
    val onError = Color(0XFFFFFFFF)
    val onErrorContainer = Color(0XFF410001)
    val background = Color(0XFFFFFBFC)
    val onBackground = Color(0XFF1D1B1E)
    val surface = Color(0XFFFFFBFC)
    val onSurface = Color(0XFF1D1B1E)
    val surfaceVariant = Color(0XFFE8DFEB)
    val onSurfaceVariant = Color(0XFF4A454E)
    val outline = Color(0XFF7B757E)
    val inverseOnSurface = Color(0XFFF5EFF3)
    val inverseSurface = Color(0XFF323033)

    fun createScheme(): ColorScheme = lightColorScheme(
        primary = primary,
        onPrimary = onPrimary,
        primaryContainer = primaryContainer,
        onPrimaryContainer = onPrimaryContainer,
        secondary = secondary,
        onSecondary = onSecondary,
        secondaryContainer = secondaryContainer,
        onSecondaryContainer = onSecondaryContainer,
        tertiary = tertiary,
        onTertiary = onTertiary,
        tertiaryContainer = tertiaryContainer,
        onTertiaryContainer = onTertiaryContainer,
        error = error,
        errorContainer = errorContainer,
        onError = onError,
        onErrorContainer = onErrorContainer,
        background = background,
        onBackground = onBackground,
        surface = surface,
        onSurface = onSurface,
        surfaceVariant = surfaceVariant,
        onSurfaceVariant = onSurfaceVariant,
        outline = outline,
        inverseOnSurface = inverseOnSurface,
        inverseSurface = inverseSurface
    )
}
