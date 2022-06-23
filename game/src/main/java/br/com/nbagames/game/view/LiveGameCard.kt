package br.com.nbagames.game.view

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.nbagames.designsystem.components.TextField
import br.com.nbagames.designsystem.components.loading.ImageLoader
import br.com.nbagames.designsystem.extension.formatGameClock
import br.com.nbagames.designsystem.extension.formatNumberTwoDigits
import br.com.nbagames.designsystem.theme.NbaGamesTheme
import br.com.nbagames.game.R
import br.com.nbagames.model.Team
import br.com.nbagames.game.presentation.LiveGamePresentation

@Composable
fun LiveGameCard(
    modifier: Modifier = Modifier,
    liveGame: LiveGamePresentation,
    onLiveGameClick: (gameId: Int) -> Unit = {}
) {
    Card(
        elevation = 4.dp,
        modifier = modifier.clickable(onClick = { onLiveGameClick(liveGame.id) })
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TeamIdentification(
                team = liveGame.homeTeam,
                modifier = Modifier.weight(1f)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(2f)
            ) {
                GameScoreBoard(homePoints = liveGame.homePoints, visitantPoints = liveGame.visitantPoints)
                Spacer(modifier = Modifier.size(6.dp))
                liveGame.gameClock?.let { gameGlock -> GameClock(clockTime = gameGlock) }
                Spacer(modifier = Modifier.size(6.dp))
                GameQuarter(quarter = liveGame.quarter)
            }
            TeamIdentification(
                team = liveGame.visitantTeam,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun TeamIdentification(
    modifier: Modifier = Modifier,
    team: Team
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ImageLoader(
            imageUrl = team.logo,
            contentDescription = team.name,
            defaultContentResource = R.drawable.default_team_logo,
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = team.name,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelMedium,
            color = Color.Black
        )
    }
}

@Composable
private fun GameScoreBoard(
    modifier: Modifier = Modifier,
    homePoints: Int,
    visitantPoints: Int,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            modifier = Modifier.defaultMinSize(minWidth = 46.dp),
            text = homePoints.formatNumberTwoDigits(),
            fontSize = 26.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.width(16.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_versus_grey),
            contentDescription = null,
            modifier = Modifier.size(12.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        TextField(
            modifier = Modifier.defaultMinSize(minWidth = 46.dp),
            text = visitantPoints.formatNumberTwoDigits(),
            fontSize = 26.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun GameClock(
    modifier: Modifier = Modifier,
    clockTime: String,
) {
    if (clockTime.isNotEmpty()) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_clock_grey),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            TextField(
                text = clockTime.formatGameClock(),
                color = Color.Black,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Light,
            )
        }
    }
}

@Composable
fun GameQuarter(@StringRes quarter: Int) {
    TextField(
        text = stringResource(id = quarter),
        color = Color.Black,
        fontSize = 12.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Light,
    )
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val liveGame = LiveGamePresentation(
        id = 1,
        homeTeam = Team(
            id = 1,
            name = "Miami Heat",
            nickname = "MHT",
            logo = "https:\\/\\/upload.wikimedia.org\\/wikipedia\\/fr\\/thumb\\/d\\/de\\/Houston_Rockets_logo_2003.png\\/330px-Houston_Rockets_logo_2003.png"
        ),
        visitantTeam = Team(
            id = 2,
            name = "Brooklyn Nets",
            nickname = "BNT",
            logo = "https://upload.wikimedia.org/wikipedia/fr/8/89/Raptors2015.png"
        ),
        homePoints = 10,
        visitantPoints = 11,
        gameClock = "2:37",
        quarter = R.string.first_quarter
    )

    NbaGamesTheme {
        Surface(modifier = Modifier.padding(10.dp)) {
            LiveGameCard(
                liveGame = liveGame,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}
