package br.com.nbagames.game.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
    liveGameCardBackground: LiveGameCardBackground = LiveGameCardBackground.Panel,
    onLiveGameClick: (gameId: String) -> Unit = {}
) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .clickable(onClick = { onLiveGameClick("0") })
    ) {
        CardBackground(cardBackgroundIdRes = liveGameCardBackground.backgroundRes)
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            TeamLogo(
                teamName = liveGame.homeTeam,
                teamLogoUrl = "https://upload.wikimedia.org/wikipedia/fr/e/ee/Hawks_2016.png"
            )
            Image(
                painter = painterResource(id = R.drawable.ic_versus_white),
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )
            TeamLogo(teamName = liveGame.awayTeam, teamLogoUrl = "")
        }
    }
}

@Composable
private fun CardBackground(
    cardBackgroundIdRes: Int
) {
    Image(
        painter = painterResource(id = cardBackgroundIdRes),
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.9f),
        contentScale = ContentScale.Fit
    )
}

@Composable
private fun TeamLogo(
    teamName: String,
    teamLogoUrl: String
) {
    val painter = rememberCoilPainter(
        request = teamLogoUrl,
        fadeIn = true
    )

    val painterResource = if (painter.loadState is ImageLoadState.Success) {
        painter
    } else {
        painterResource(R.drawable.default_team_logo)
    }

    Image(
        painter = painterResource,
        contentDescription = teamName,
        modifier = Modifier.size(60.dp),
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