package br.com.nbagames.game.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
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
import br.com.nbagames.model.toQuarter

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
        Column(modifier = modifier.padding(top = mediumPadding, start = mediumPadding)) {
            val cellSize = 60.dp

            Text(
                text = stringResource(id = R.string.score_by_quarter),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier.size(mediumPadding))
            Row {
                TeamIdentification(
                    homeTeamName = homeTeamName,
                    visitorTeamName = visitorTeamName,
                    itemWidth = cellSize,
                    modifier = Modifier.width(cellSize)
                )
                LazyHorizontalGrid(
                    modifier = Modifier.height(cellSize * 3),
                    rows = GridCells.Fixed(3)
                ) {
                    item {
                        QuarterQuarterHistoryHeader(
                            modifier = Modifier.height(cellSize),
                            currentQuarter = currentQuarter,
                            isGameFinished = isGameFinished,
                            totalQuarters = quarterScoreHistory.homeScore.size,
                            itemWidth = cellSize
                        )
                    }
                    item {
                        TeamQuarterHistory(
                            modifier = Modifier.height(cellSize),
                            scoreHistory = quarterScoreHistory.homeScore,
                            totalPoints = totalHomePoints,
                            currentQuarter = currentQuarter,
                            isGameFinished = isGameFinished,
                            itemWidth = cellSize
                        )
                    }
                    item {
                        TeamQuarterHistory(
                            modifier = Modifier.height(cellSize),
                            scoreHistory = quarterScoreHistory.visitorScore,
                            totalPoints = totalVisitorPoints,
                            currentQuarter = currentQuarter,
                            isGameFinished = isGameFinished,
                            itemWidth = cellSize
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TeamIdentification(
    modifier: Modifier = Modifier,
    homeTeamName: String,
    visitorTeamName: String,
    itemWidth: Dp
) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.size(itemWidth))
        SquareTextView(
            modifier = Modifier.size(itemWidth),
            text = homeTeamName,
            style = MaterialTheme.typography.titleMedium
        )
        SquareTextView(
            modifier = Modifier.size(itemWidth),
            text = visitorTeamName,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
fun QuarterQuarterHistoryHeader(
    modifier: Modifier = Modifier,
    totalQuarters: Int,
    currentQuarter: Quarter,
    isGameFinished: Boolean,
    itemWidth: Dp
) {
    Row(modifier = modifier) {
        val hasOvertime = totalQuarters > Quarter.values().size
        for (index in 1..totalQuarters) {
            val isOvertime = index > Quarter.values().size
            var textColor = Color.Black
            var backgroundColor = Color.White

            val headerText = if (isOvertime) {
                if (index == totalQuarters && !isGameFinished) {
                    textColor = Color.White
                    backgroundColor = CustomColors.blackCurrant
                }

                val currentOvertime = index - Quarter.values().size
                stringResource(id = R.string.overtime, currentOvertime)
            } else {
                if (!hasOvertime && index == currentQuarter.code && !isGameFinished) {
                    textColor = Color.White
                    backgroundColor = CustomColors.blackCurrant
                }

                index.toQuarter().shortDescription
            }

            SquareTextView(
                modifier = Modifier.size(itemWidth),
                text = headerText,
                textColor = textColor,
                backgroundColor = backgroundColor,
                style = MaterialTheme.typography.titleMedium
            )
        }

        var totalTextColor = Color.Black
        var totalBackgroundColor = Color.White
        if (isGameFinished) {
            totalTextColor = Color.White
            totalBackgroundColor = CustomColors.blackCurrant
        }
        SquareTextView(
            modifier = Modifier.size(itemWidth),
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
    currentQuarter: Quarter,
    totalPoints: Int,
    isGameFinished: Boolean,
    scoreHistory: List<Int>,
    itemWidth: Dp
) {
    Row(modifier = modifier) {
        val hasOvertime = scoreHistory.size > Quarter.values().size
        scoreHistory.forEachIndexed { index, score ->
            val isOvertime = index >= Quarter.values().size
            var textColor = Color.Black
            var backgroundColor = Color.White

            if (!isGameFinished) {
                if (!hasOvertime && !isOvertime && currentQuarter.code == index + 1) {
                    textColor = Color.White
                    backgroundColor = CustomColors.blackCurrant
                } else if (isOvertime && index + 1 == scoreHistory.size) {
                    textColor = Color.White
                    backgroundColor = CustomColors.blackCurrant
                }
            }

            SquareTextView(
                modifier = Modifier.size(itemWidth),
                text = score.toString(),
                textColor = textColor,
                backgroundColor = backgroundColor,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        var totalTextColor = Color.Black
        var totalBackgroundColor = Color.White
        if (isGameFinished) {
            totalTextColor = Color.White
            totalBackgroundColor = CustomColors.blackCurrant
        }
        SquareTextView(
            modifier = Modifier.size(itemWidth),
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
    textColor: Color = Color.Black,
    backgroundColor: Color = Color.White,
    style: TextStyle
) {
    Box(
        modifier = modifier.background(backgroundColor),
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
    val quarter = Quarter.Second
    val quarterScoreHistory = QuarterScoreHistory(
        homeScore = listOf(10, 15, 20, 22),
        visitorScore = listOf(12, 17, 11, 22)
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
                isGameFinished = false
            )
        }
    }
}
