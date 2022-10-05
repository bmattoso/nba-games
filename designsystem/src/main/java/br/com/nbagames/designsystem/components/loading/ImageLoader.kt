package br.com.nbagames.designsystem.components.loading

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter

@Composable
fun ImageLoader(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentDescription: String,
    @DrawableRes defaultContentResource: Int
) {
    val painter = rememberAsyncImagePainter(
        model = imageUrl,
        placeholder = painterResource(defaultContentResource),
        fallback = painterResource(defaultContentResource)
    )

    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = ContentScale.Fit
    )
}
