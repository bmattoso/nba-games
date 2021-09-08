package br.com.nbagames.designsystem.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.nbagames.designsystem.R

@Composable
fun RollingBall() {
    DefaultLottieAnimation(
        animationResId = R.raw.rolling_ball,
        modifier = Modifier.size(350.dp)
    )
}
