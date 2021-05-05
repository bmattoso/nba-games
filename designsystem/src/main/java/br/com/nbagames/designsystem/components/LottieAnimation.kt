package br.com.nbagames.designsystem.components

import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieAnimationSpec

@Composable
fun DefaultLottieAnimation(
    @RawRes animationResId: Int,
    modifier: Modifier
) {
    val animationSpec = remember { LottieAnimationSpec.RawRes(animationResId) }

    LottieAnimation(
        spec = animationSpec,
        modifier = modifier
    )
}