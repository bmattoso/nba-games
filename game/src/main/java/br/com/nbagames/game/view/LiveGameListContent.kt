package br.com.nbagames.game.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.nbagames.designsystem.components.TextField
import br.com.nbagames.game.R

@Composable
fun LiveGameListContent(
    liveGames: List<Any>,
    onLiveGameClick: (gameId: String) -> Unit
) {
    BoxWithConstraints(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
        Column(Modifier.fillMaxSize().background(MaterialTheme.colors.primary)) {
            TextField(
                text = stringResource(id = R.string.live_games_list),
                fontSize = MaterialTheme.typography.h5.fontSize
            )
            LazyColumn(
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    count = liveGames.size,
                    itemContent = { index ->
                        val currentLiveGame = liveGames[index]
                        val selectedBackground = getCardBackground(index).backgroundUrl
                        LiveGameCard(
                            cardBackgroundUrl = selectedBackground,
                            liveGame = currentLiveGame,
                            onLiveGameClick = onLiveGameClick
                        )
                    }
                )
            }
        }
    }
}

private fun getCardBackground(index: Int): LiveGameCardBackground {
    val allBackgrounds = LiveGameCardBackground.values()
    return allBackgrounds[index % allBackgrounds.size]
}
