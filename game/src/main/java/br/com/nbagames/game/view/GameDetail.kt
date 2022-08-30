package br.com.nbagames.game.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.nbagames.designsystem.components.TextField

@Composable
fun GameDetail(
    modifier: Modifier = Modifier,
    gameId: Int,
    onPlayerClick: (playerId: Int) -> Unit,
    onTeamClick: (teamId: Int) -> Unit,
) {
    TextField(text = "Detalhe dos jogos - $gameId")
}
