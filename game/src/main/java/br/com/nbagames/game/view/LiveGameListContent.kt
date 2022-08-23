package br.com.nbagames.game.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.nbagames.designsystem.theme.largePadding
import br.com.nbagames.designsystem.theme.mediumPadding
import br.com.nbagames.game.presentation.LiveGamePresentation

@Composable
fun LiveGameListContent(
    modifier: Modifier = Modifier,
    liveGames: List<LiveGamePresentation>,
    onLiveGameClick: (gameId: Int) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(mediumPadding)
    ) {
        items(
            count = liveGames.size,
            itemContent = { index ->
                LiveGameCard(
                    liveGame = liveGames[index],
                    onLiveGameClick = onLiveGameClick,
                    modifier = Modifier
                        .defaultMinSize(minHeight = 160.dp)
                        .padding(horizontal = largePadding, vertical = mediumPadding)
                )
            }
        )
    }
}
