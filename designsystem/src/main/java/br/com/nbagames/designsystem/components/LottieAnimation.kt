package br.com.nbagames.designsystem.components

import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun DefaultLottieAnimation(
    @RawRes animationResId: Int,
    modifier: Modifier,
    iterations: Int = LottieConstants.IterateForever
) {
    val animationComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(animationResId)
    )

    LottieAnimation(
        composition = animationComposition,
        iterations = iterations,
        modifier = modifier
    )
}
