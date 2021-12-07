package br.com.nbagames.game.view

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.nbagames.designsystem.components.ImageLoader
import br.com.nbagames.designsystem.components.TextField
import br.com.nbagames.designsystem.theme.NbaGamesTheme
import br.com.nbagames.game.R
import br.com.nbagames.model.Team
import br.com.nbagames.usecase.game.presentation.LiveGamePresentation

@Composable
fun LiveGameCard(
    liveGame: LiveGamePresentation,
    onLiveGameClick: (gameId: String) -> Unit = {}
) {
    Card(
        elevation = 4.dp,
        backgroundColor = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onLiveGameClick(liveGame.id) })
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            TeamIdentification(liveGame.homeTeam)
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(120.dp)
                    .align(Alignment.CenterVertically),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GameScoreBoard(homePoints = liveGame.homePoints, visitantPoints = liveGame.visitantPoints)
                Spacer(modifier = Modifier.size(6.dp))
                GameClock(clockTime = liveGame.gameClock)
                Spacer(modifier = Modifier.size(6.dp))
                GameQuarter(quarter = liveGame.quarter)
            }
            TeamIdentification(liveGame.visitantTeam)
        }
    }
}

@Composable
private fun TeamIdentification(team: Team) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(top = 12.dp, bottom = 12.dp)
            .size(90.dp)
    ) {
        ImageLoader(
            imageUrl = team.logo,
            contentDescription = team.fullName,
            defaultContentResource = R.drawable.default_team_logo,
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            text = team.fullName,
            color = Color.Black,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun GameScoreBoard(homePoints: Int, visitantPoints: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextField(
            text = homePoints.toString(),
            fontSize = 26.sp,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_versus_grey),
            contentDescription = null,
            modifier = Modifier
                .size(12.dp)
                .align(Alignment.CenterVertically)
        )
        TextField(
            text = visitantPoints.toString(),
            fontSize = 26.sp,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
private fun GameClock(clockTime: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_clock_grey),
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.size(10.dp))
        TextField(
            text = clockTime,
            color = Color.Black,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Light,
        )
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
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val liveGame = LiveGamePresentation(
        id = "1",
        homeTeam = Team(
            id = "1",
            fullName = "Miami Heat",
            shortName = "MHT",
            logo = "https:\\/\\/upload.wikimedia.org\\/wikipedia\\/fr\\/thumb\\/d\\/de\\/Houston_Rockets_logo_2003.png\\/330px-Houston_Rockets_logo_2003.png"
        ),
        visitantTeam = Team(
            id = "2",
            fullName = "Brooklyn Nets",
            shortName = "BNT",
            logo = "https://upload.wikimedia.org/wikipedia/fr/8/89/Raptors2015.png"
        ),
        homePoints = 10,
        visitantPoints = 11,
        gameClock = "2:37",
        quarter = R.string.first_quarter
    )

    NbaGamesTheme {
        Scaffold(
            modifier = Modifier
                .padding(10.dp)
                .background(colorResource(id = R.color.brightGrayLight))
        ) {
            LiveGameCard(liveGame = liveGame)
        }
    }
}
