package br.com.nbagames.game.view

import androidx.compose.runtime.Composable
import br.com.nbagames.designsystem.components.TextField

@Composable
fun LiveGameCard(
    liveGame: Any,
    cardBackgroundUrl: String = LiveGameCardBackground.Panel.backgroundUrl,
    onLiveGameClick: (gameId: String) -> Unit = {}
) {
    TextField(text = "Current live Game")
}
