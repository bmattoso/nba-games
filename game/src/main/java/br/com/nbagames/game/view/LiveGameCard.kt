package br.com.nbagames.game.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.nbagames.designsystem.components.ImageLoader
import br.com.nbagames.designsystem.components.TextField
import br.com.nbagames.designsystem.theme.NbaGamesTheme
import br.com.nbagames.game.R
import br.com.nbagames.model.LiveGame
import br.com.nbagames.model.Team

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
            .clickable(onClick = { onLiveGameClick(liveGame.id) })
    ) {
        CardBackground(cardBackgroundIdRes = liveGameCardBackground.backgroundRes)
        Column {
            GameIdentification(liveGame)
            GameInformation(liveGame)
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
            .alpha(0.80f),
        contentScale = ContentScale.Fit
    )
}

@Composable
private fun GameIdentification(liveGame: LiveGame) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        TeamIdentification(liveGame.homeTeam)
        Image(
            painter = painterResource(id = R.drawable.ic_versus_white),
            contentDescription = null,
            modifier = Modifier.size(25.dp)
        )
        TeamIdentification(liveGame.awayTeam)
    }
}

@Composable
private fun TeamIdentification(team: Team) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageLoader(
            imageUrl = team.logo,
            contentDescription = team.nickName,
            defaultContentResource = R.drawable.default_team_logo,
            modifier = Modifier.size(60.dp)
        )
        TextField(
            text = team.nickName,
            color = Color.White
        )
    }
}

@Composable
fun GameInformation(liveGame: LiveGame) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val gameScore = "${liveGame.homePoints} : ${liveGame.awayPoints}"

        TextField(
            text = gameScore,
            color = Color.White,
            fontSize = 40.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_clock),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.size(10.dp))
            TextField(
                text = liveGame.currentTime,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val liveGame = LiveGame(
        id = "1",
        homeTeam = Team(
            id = "1",
            fullName = "Miami Heat",
            nickName = "Heat",
            shortName = "MHT",
            logo = ""
        ),
        awayTeam = Team(
            id = "2",
            fullName = "Brooklyn Nets",
            nickName = "Nets",
            shortName = "BNT",
            logo = ""
        ),
        homePoints = 10,
        awayPoints = 11,
        currentTime = "2:37"
    )

    NbaGamesTheme {
        LiveGameCard(
            liveGame = liveGame
        )
    }
}
