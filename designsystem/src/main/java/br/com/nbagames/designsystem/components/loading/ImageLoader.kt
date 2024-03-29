package br.com.nbagames.designsystem.components.loading

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter

@Composable
fun ImageLoader(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    contentDescription: String,
    @DrawableRes defaultContentResource: Int
) {
    if (!imageUrl.isNullOrBlank()) {
        val painter = rememberAsyncImagePainter(
            model = imageUrl,
            placeholder = painterResource(defaultContentResource),
            fallback = painterResource(defaultContentResource)
        )

        Image(
            modifier = modifier,
            painter = painter,
            contentDescription = contentDescription,
            contentScale = ContentScale.Fit
        )
    } else {
        Image(
            modifier = modifier,
            painter = painterResource(id = defaultContentResource),
            contentDescription = contentDescription,
            contentScale = ContentScale.Fit
        )
    }
}
