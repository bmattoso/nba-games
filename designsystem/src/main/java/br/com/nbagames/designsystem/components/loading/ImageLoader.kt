package br.com.nbagames.designsystem.components.loading

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberImagePainter

@Composable
fun ImageLoader(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentDescription: String,
    @DrawableRes defaultContentResource: Int
) {
    val painter = rememberImagePainter(
        data = imageUrl,
        builder = {
            crossfade(true)
            placeholder(defaultContentResource)
        }
    )

    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = ContentScale.Fit
    )
}
