package br.com.nbagames.game.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.nbagames.usecase.game.presentation.LiveGamePresentation

@Composable
fun LiveGameListContent(
    modifier: Modifier = Modifier,
    liveGames: List<LiveGamePresentation>,
    onLiveGameClick: (gameId: String) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            count = liveGames.size,
            itemContent = { index ->
                LiveGameCard(
                    liveGame = liveGames[index],
                    onLiveGameClick = onLiveGameClick,
                    modifier = Modifier
                        .defaultMinSize(minHeight = 150.dp)
                        .padding(horizontal = 16.dp, vertical = 10.dp)
                )
            }
        )
    }
}
