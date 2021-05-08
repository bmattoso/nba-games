package br.com.nbagames.designsystem.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.nbagames.designsystem.R
import com.airbnb.lottie.compose.rememberLottieAnimationState

@Composable
fun RollingBall() {
    val animationState = rememberLottieAnimationState(
        autoPlay = true, repeatCount = Integer.MAX_VALUE
    )
    animationState.speed = 1.5f

    DefaultLottieAnimation(
        animationResId = R.raw.rolling_ball,
        modifier = Modifier.size(350.dp),
        animationState = animationState
    )
}
