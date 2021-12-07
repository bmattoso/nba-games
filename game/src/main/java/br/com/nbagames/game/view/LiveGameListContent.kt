package br.com.nbagames.game.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.nbagames.usecase.game.presentation.LiveGamePresentation

@Composable
fun LiveGameListContent(
    liveGames: List<LiveGamePresentation>,
    onLiveGameClick: (gameId: String) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(end = 16.dp, start = 16.dp, top = 16.dp, bottom = 60.dp)
    ) {
        items(
            count = liveGames.size,
            itemContent = { index ->
                LiveGameCard(
                    liveGame = liveGames[index],
                    onLiveGameClick = onLiveGameClick
                )
            }
        )
    }
}
