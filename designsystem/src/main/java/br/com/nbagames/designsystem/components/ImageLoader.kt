package br.com.nbagames.designsystem.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter

@ExperimentalCoilApi
@Composable
fun ImageLoader(
    imageUrl: String,
    contentDescription: String,
    @DrawableRes defaultContentResource: Int,
    modifier: Modifier = Modifier
) {
    val painter = rememberImagePainter(
        data = imageUrl,
        builder = { crossfade(true) }
    )

    val painterResource = if (painter.state is ImagePainter.State.Success) {
        painter
    } else {
        painterResource(defaultContentResource)
    }

    Image(
        painter = painterResource,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = ContentScale.Fit
    )
}
