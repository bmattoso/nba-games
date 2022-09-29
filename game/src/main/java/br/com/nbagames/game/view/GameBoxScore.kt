package br.com.nbagames.game.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import br.com.nbagames.designsystem.theme.defaultCellSize
import br.com.nbagames.designsystem.theme.extraSmallPadding
import br.com.nbagames.designsystem.theme.mediumPadding
import br.com.nbagames.designsystem.theme.smallLineHeight
import br.com.nbagames.game.R
import br.com.nbagames.model.player.Player
import br.com.nbagames.model.statistics.GameStatistics
import br.com.nbagames.model.statistics.PlayerStatistics
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun GameBoxScore(
    modifier: Modifier = Modifier,
    homeTeamName: String,
    visitorTeamName: String,
    gameStatistics: GameStatistics,
    onPlayerClick: (playerId: Int) -> Unit
) {
    val pagerState = rememberPagerState()

    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.players_statistics),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(mediumPadding))
        BoxHeader(
            homeTeamName = homeTeamName,
            visitorTeamName = visitorTeamName,
            pagerState = pagerState
        )
        BoxContent(
            pagerState = pagerState,
            statistics = gameStatistics
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BoxHeader(
    modifier: Modifier = Modifier,
    homeTeamName: String,
    visitorTeamName: String,
    pagerState: PagerState
) {
    val scope = rememberCoroutineScope()
    TabRow(
        modifier = modifier,
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = CustomColors.blackCurrant,
        contentColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = smallLineHeight,
                color = Color.White
            )
        }
    ) {
        Tab(
            text = {
                Text(
                    text = homeTeamName,
                    color = if (pagerState.currentPage == 0) Color.White else Color.Gray,
                    textAlign = TextAlign.Center
                )
            },
            selected = pagerState.currentPage == 0,
            onClick = { scope.launch { pagerState.animateScrollToPage(0) } }
        )
        Tab(
            text = {
                Text(
                    text = visitorTeamName,
                    color = if (pagerState.currentPage == 1) Color.White else Color.Gray,
                    textAlign = TextAlign.Center
                )
            },
            selected = pagerState.currentPage == 1,
            onClick = { scope.launch { pagerState.animateScrollToPage(1) } }
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BoxContent(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    statistics: GameStatistics
) {
    HorizontalPager(
        modifier = modifier,
        count = 2,
        state = pagerState,
        userScrollEnabled = false
    ) { page ->
        when (page) {
            0 -> StatisticsByPlayer(
                modifier = Modifier.padding(top = extraSmallPadding, start = extraSmallPadding),
                playersStatistics = statistics.homePlayersStatistics
            )
            1 -> StatisticsByPlayer(
                modifier = Modifier.padding(top = extraSmallPadding, start = extraSmallPadding),
                playersStatistics = statistics.visitorPlayersStatistics
            )
        }
    }
}

@Composable
fun StatisticsByPlayer(
    modifier: Modifier = Modifier,
    playersStatistics: List<PlayerStatistics>
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top
    ) {
        val cellSize = 60.dp
        val firstColumnWidth = 100.dp

        Row {
            PlayersIdentification(
                modifier = Modifier
                    .width(firstColumnWidth)
                    .height(cellSize),
                players = playersStatistics
            )

            val totalRows = playersStatistics.size + 1
            LazyHorizontalGrid(
                modifier = Modifier.height(cellSize * totalRows),
                rows = GridCells.Fixed(totalRows)
            ) {
                item { StatisticsHeader(cellSize = cellSize) }

                playersStatistics.forEachIndexed { index, playerStatistics ->
                    item {
                        val lineColor = if (index % 2 == 0) CustomColors.primary70 else Color.White
                        PlayerStatisticsRow(
                            modifier = Modifier
                                .background(lineColor)
                                .size(cellSize),
                            statistics = playerStatistics
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StatisticsHeader(
    modifier: Modifier = Modifier,
    cellSize: Dp = defaultCellSize
) {
    Row(modifier = modifier) {
        val headerTextStyle = MaterialTheme.typography.titleMedium

        UnderlineText(
            modifier = Modifier.size(cellSize),
            text = stringResource(id = R.string.points_abbreviation),
            textStyle = headerTextStyle
        )
        UnderlineText(
            modifier = Modifier.size(cellSize),
            text = stringResource(id = R.string.minutes_abbreviation),
            textStyle = headerTextStyle
        )
        UnderlineText(
            modifier = Modifier.size(cellSize),
            text = stringResource(id = R.string.fouls),
            textStyle = headerTextStyle
        )
        UnderlineText(
            modifier = Modifier.size(cellSize),
            text = stringResource(id = R.string.field_goals),
            textStyle = headerTextStyle
        )
        UnderlineText(
            modifier = Modifier.size(cellSize),
            text = stringResource(id = R.string.three_points_abbreviation),
            textStyle = headerTextStyle
        )
        UnderlineText(
            modifier = Modifier.size(cellSize),
            text = stringResource(id = R.string.free_throws_abbreviation),
            textStyle = headerTextStyle
        )
        UnderlineText(
            modifier = Modifier.size(cellSize),
            text = stringResource(id = R.string.rebounds),
            textStyle = headerTextStyle
        )
        UnderlineText(
            modifier = Modifier.size(cellSize),
            text = stringResource(id = R.string.assists),
            textStyle = headerTextStyle
        )
        UnderlineText(
            modifier = Modifier.size(cellSize),
            text = stringResource(id = R.string.steals),
            textStyle = headerTextStyle
        )
        UnderlineText(
            modifier = Modifier.size(cellSize),
            text = stringResource(id = R.string.turnover_abbreviation),
            textStyle = headerTextStyle
        )
        UnderlineText(
            modifier = Modifier.size(cellSize),
            text = stringResource(id = R.string.blocks),
            textStyle = headerTextStyle
        )
    }
}

@Composable
fun PlayersIdentification(
    modifier: Modifier = Modifier,
    players: List<PlayerStatistics>
) {
    Column {
        UnderlineText(modifier = modifier, text = "")
        players.forEachIndexed { index, playerStatistic ->
            val lineColor = if (index % 2 == 0) CustomColors.primary70 else Color.White
            val playerPosition = playerStatistic.player.position
            val playerIdentification = if (playerPosition == null) {
                stringResource(
                    id = R.string.player_name_abbreviation,
                    playerStatistic.player.firstName[0],
                    playerStatistic.player.lastName
                )
            } else {
                stringResource(
                    id = R.string.player_name_position_abbreviation,
                    playerStatistic.player.firstName[0],
                    playerStatistic.player.lastName,
                    playerPosition
                )
            }
            UnderlineText(
                modifier = modifier.background(lineColor),
                text = playerIdentification
            )
        }
    }
}

@Composable
fun PlayerStatisticsRow(
    modifier: Modifier = Modifier,
    statistics: PlayerStatistics
) {
    Row {
        UnderlineText(modifier = modifier, text = statistics.points.toString())
        UnderlineText(modifier = modifier, text = statistics.minutesPlayed)
        UnderlineText(modifier = modifier, text = statistics.personalFouls.toString())
        UnderlineText(
            modifier = modifier,
            text = stringResource(
                id = R.string.player_stats_pattern,
                statistics.fieldGoalsMade,
                statistics.fieldGoalsMade,
                statistics.fieldGoalsPercentage
            )
        )
        UnderlineText(
            modifier = modifier,
            text = stringResource(
                id = R.string.player_stats_pattern,
                statistics.threePointsMade,
                statistics.threePointsAttempted,
                statistics.threePointsPercentage
            )
        )
        UnderlineText(
            modifier = modifier,
            text = stringResource(
                id = R.string.player_stats_pattern,
                statistics.freeThrowsAttempted,
                statistics.freeThrowsMade,
                statistics.freeThrowsPercentage
            )
        )
        UnderlineText(
            modifier = modifier,
            text = stringResource(
                id = R.string.player_stats_pattern,
                statistics.offensiveRebound,
                statistics.defensiveRebounds,
                statistics.totalRebounds
            )
        )
        UnderlineText(modifier = modifier, text = statistics.assists.toString())
        UnderlineText(modifier = modifier, text = statistics.steals.toString())
        UnderlineText(modifier = modifier, text = statistics.turnovers.toString())
        UnderlineText(modifier = modifier, text = statistics.blocks.toString())
    }
}

@Composable
fun UnderlineText(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium
) {
    Box(modifier, contentAlignment = Alignment.Center) {
        // Canvas(modifier = Modifier.matchParentSize()) {
        //     drawLine(
        //         strokeWidth = 2f,
        //         start = Offset(x = 0f, y = size.height - 2),
        //         end = Offset(x = size.width, y = size.height - 2),
        //         color = Color.Black
        //     )
        // }

        Text(
            text = text,
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = textStyle
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GameBoxScorePreview() {
    val playersStatistics = listOf(
        PlayerStatistics(
            player = Player(1, "Devin", "Booker", position = "FG"),
            points = 11,
            steals = 1,
            assists = 2,
            turnovers = 3,
            blocks = 0,
            personalFouls = 1,
            minutesPlayed = "6:33",
            comment = "",
            plusMinus = "",
            fieldGoalsAttempted = 0,
            fieldGoalsMade = 0,
            fieldGoalsPercentage = "",
            freeThrowsAttempted = 0,
            freeThrowsMade = 0,
            freeThrowsPercentage = "",
            threePointsAttempted = 0,
            threePointsMade = 0,
            threePointsPercentage = "",
            offensiveRebound = 0,
            defensiveRebounds = 0,
            totalRebounds = 0
        ),
        PlayerStatistics(
            player = Player(2, "Jason", "Tatum", position = "SG"),
            points = 11,
            steals = 1,
            assists = 2,
            turnovers = 3,
            blocks = 0,
            personalFouls = 1,
            minutesPlayed = "6:33",
            comment = "",
            plusMinus = "",
            fieldGoalsAttempted = 0,
            fieldGoalsMade = 0,
            fieldGoalsPercentage = "",
            freeThrowsAttempted = 0,
            freeThrowsMade = 0,
            freeThrowsPercentage = "",
            threePointsAttempted = 0,
            threePointsMade = 0,
            threePointsPercentage = "",
            offensiveRebound = 0,
            defensiveRebounds = 0,
            totalRebounds = 0
        )
    )

    val gameStatistics = GameStatistics(
        homePlayersStatistics = playersStatistics,
        visitorPlayersStatistics = playersStatistics
    )

    NbaGamesTheme {
        GameBoxScore(
            modifier = Modifier
                .padding(mediumPadding)
                .fillMaxSize(),
            homeTeamName = "Miami Heat",
            visitorTeamName = "Golden State Warriors",
            gameStatistics = gameStatistics,
            onPlayerClick = {}
        )
    }
}
