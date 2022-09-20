package br.com.nbagames.game.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.nbagames.designsystem.color.CustomColors
import br.com.nbagames.designsystem.theme.NbaGamesTheme
import br.com.nbagames.designsystem.theme.defaultElevation
import br.com.nbagames.designsystem.theme.mediumPadding
import br.com.nbagames.game.R
import br.com.nbagames.model.Quarter
import br.com.nbagames.model.QuarterScoreHistory
import br.com.nbagames.model.Team

@Composable
fun GameQuarterHistory(
    modifier: Modifier = Modifier,
    homeTeamName: String,
    visitorTeamName: String,
    totalHomePoints: Int,
    totalVisitorPoints: Int,
    currentQuarter: Quarter,
    isGameFinished: Boolean,
    quarterScoreHistory: QuarterScoreHistory
) {
    Card(elevation = defaultElevation) {
        Column(modifier = modifier.padding(mediumPadding)) {
            Text(
                text = stringResource(id = R.string.score_by_quarter),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier.size(mediumPadding))
            QuarterQuarterHistoryHead(currentQuarter = currentQuarter, isGameFinished = isGameFinished)
            TeamQuarterHistory(
                teamName = homeTeamName,
                scoreHistory = quarterScoreHistory.homeScore,
                totalPoints = totalHomePoints,
                currentQuarter = currentQuarter,
                isGameFinished = isGameFinished
            )
            TeamQuarterHistory(
                teamName = visitorTeamName,
                scoreHistory = quarterScoreHistory.visitorScore,
                totalPoints = totalVisitorPoints,
                currentQuarter = currentQuarter,
                isGameFinished = isGameFinished
            )
        }
    }
}

@Composable
fun QuarterQuarterHistoryHead(
    modifier: Modifier = Modifier,
    currentQuarter: Quarter,
    isGameFinished: Boolean
) {
    Row {
        Spacer(modifier = modifier.weight(1f))
        Quarter.values().forEach { quarter ->
            val textColor = if (currentQuarter == quarter && !isGameFinished) Color.White else Color.Black
            val backgroundColor = if (currentQuarter == quarter && !isGameFinished) CustomColors.blackCurrant else Color.White

            SquareTextView(
                modifier = modifier.weight(1f),
                text = quarter.code,
                textColor = textColor,
                backgroundColor = backgroundColor,
                style = MaterialTheme.typography.titleMedium
            )
        }
        val totalTextColor = if (isGameFinished) Color.White else Color.Black
        val totalBackgroundColor = if (isGameFinished) CustomColors.blackCurrant else Color.White
        SquareTextView(
            modifier = modifier.weight(1f),
            text = stringResource(id = R.string.total),
            textColor = totalTextColor,
            backgroundColor = totalBackgroundColor,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
fun TeamQuarterHistory(
    modifier: Modifier = Modifier,
    teamName: String,
    currentQuarter: Quarter,
    totalPoints: Int,
    isGameFinished: Boolean,
    scoreHistory: List<Int>
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        SquareTextView(
            modifier = modifier.weight(1f),
            text = teamName,
            style = MaterialTheme.typography.titleMedium
        )
        Quarter.values().forEachIndexed { index, quarter ->
            val score = if (index < scoreHistory.size) scoreHistory[index] else 0
            val textColor = if (currentQuarter == quarter && !isGameFinished) Color.White else Color.Black
            val backgroundColor =
                if (currentQuarter == quarter && !isGameFinished) CustomColors.blackCurrant else Color.White

            SquareTextView(
                modifier = modifier.weight(1f),
                text = score.toString(),
                textColor = textColor,
                backgroundColor = backgroundColor,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        val totalTextColor = if (isGameFinished) Color.White else Color.Black
        val totalBackgroundColor = if (isGameFinished) CustomColors.blackCurrant else Color.White
        SquareTextView(
            modifier = modifier.weight(1f),
            text = totalPoints.toString(),
            textColor = totalTextColor,
            backgroundColor = totalBackgroundColor,
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Composable
private fun SquareTextView(
    modifier: Modifier = Modifier,
    text: String,
    height: Dp = 30.dp,
    textColor: Color = Color.Black,
    backgroundColor: Color = Color.White,
    style: TextStyle
) {
    Box(
        modifier = modifier
            .background(backgroundColor)
            .height(height),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            color = textColor,
            style = style
        )
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun GameQuarterHistoryPreview() {
    val homeTeam = Team(
        id = 1,
        name = "Miami Heat",
        nickname = "MIA",
        logo = "https://upload.wikimedia.org/wikipedia/fr/thumb/d/de/Houston_Rockets_logo_2003.png/330px" +
            "-Houston_Rockets_logo_2003.png"
    )
    val visitorTeam = Team(
        id = 2,
        name = "Brooklyn Nets",
        nickname = "BKN",
        logo = "https://upload.wikimedia.org/wikipedia/fr/8/89/Raptors2015.png"
    )
    val quarter = Quarter.First
    val quarterScoreHistory = QuarterScoreHistory(
        homeScore = listOf(10, 15, 20),
        visitorScore = listOf(12, 17, 11)
    )

    NbaGamesTheme {
        Surface(modifier = Modifier.padding(10.dp)) {
            GameQuarterHistory(
                homeTeamName = homeTeam.nickname,
                visitorTeamName = visitorTeam.nickname,
                quarterScoreHistory = quarterScoreHistory,
                currentQuarter = quarter,
                totalVisitorPoints = 40,
                totalHomePoints = 45,
                isGameFinished = true
            )
        }
    }
}
