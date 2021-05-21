package br.com.nbagames.designsystem.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState

@Composable
fun ImageLoader(
    imageUrl: String,
    contentDescription: String,
    @DrawableRes defaultContentResource: Int,
    modifier: Modifier = Modifier
) {
    val painter = rememberCoilPainter(
        request = imageUrl,
        fadeIn = true
    )

    val painterResource = if (painter.loadState is ImageLoadState.Success) {
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
