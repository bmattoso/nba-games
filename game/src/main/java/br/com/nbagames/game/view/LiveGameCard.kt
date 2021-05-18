package br.com.nbagames.game.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.nbagames.designsystem.theme.NbaGamesTheme
import br.com.nbagames.game.R
import br.com.nbagames.model.LiveGame
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState

@Composable
fun LiveGameCard(
    liveGame: LiveGame,
    cardBackgroundUrl: String = LiveGameCardBackground.Panel.backgroundUrl,
    onLiveGameClick: (gameId: String) -> Unit = {}
) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f)
            .clickable(onClick = { onLiveGameClick("0") })
    ) {
        CardBackground(cardBackgroundUrl = cardBackgroundUrl)
        Box {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(20.dp)
            ) {
                TeamLogo(teamName = liveGame.homeTeam, teamLogoUrl = "https://upload.wikimedia.org/wikipedia/fr/e/ee/Hawks_2016.png")
                Text(text = "vs")
                TeamLogo(teamName = liveGame.awayTeam, teamLogoUrl = "")
            }
        }
    }
}

@Composable
private fun CardBackground(
    cardBackgroundUrl: String
) {
    val painter = rememberCoilPainter(cardBackgroundUrl)

    if (painter.loadState is ImageLoadState.Success) {
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun TeamLogo(
    teamName: String,
    teamLogoUrl: String
) {
    val painter = rememberCoilPainter(teamLogoUrl)

    val painterResource = if (painter.loadState is ImageLoadState.Success) {
        painter
    } else {
        painterResource(R.drawable.default_team_logo)
    }

    Image(
        painter = painterResource,
        contentDescription = teamName,
        modifier = Modifier.size(50.dp),
        contentScale = ContentScale.Fit
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val liveGame = LiveGame(
        id = "1",
        homeTeam = "Miami Heat",
        awayTeam = "Brooklyn Nets",
        homePoints = 10,
        awayPoints = 11
    )

    NbaGamesTheme {
        LiveGameCard(
            liveGame = liveGame
        )
    }
}