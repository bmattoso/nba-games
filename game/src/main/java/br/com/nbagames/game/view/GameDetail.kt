package br.com.nbagames.game.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.nbagames.designsystem.theme.largePadding
import br.com.nbagames.designsystem.theme.mediumPadding
import br.com.nbagames.game.R
import br.com.nbagames.game.presentation.GamePresentation
import br.com.nbagames.game.presentation.detail.GameDetailViewModel
import br.com.nbagames.model.Team
import org.koin.androidx.compose.getViewModel

@Composable
fun GameDetail(
    modifier: Modifier = Modifier,
    gameId: Int,
    onPlayerClick: (playerId: Int) -> Unit,
    onTeamClick: (teamId: Int) -> Unit,
    viewModel: GameDetailViewModel = getViewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value

    Column(modifier = modifier) {
        if (uiState.game != null) {
            GameCard(
                game = uiState.game,
                modifier = Modifier
                    .defaultMinSize(minHeight = 160.dp)
                    .padding(horizontal = largePadding, vertical = mediumPadding)
            )
        } else {
            viewModel.loadGameDetails(gameId)
        }
    }
}
