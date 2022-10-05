package br.com.nbagames.game.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min
import br.com.nbagames.designsystem.color.CustomColors
import br.com.nbagames.designsystem.theme.NbaGamesTheme
import br.com.nbagames.designsystem.theme.defaultCellSize
import br.com.nbagames.designsystem.theme.mediumPadding
import br.com.nbagames.designsystem.theme.smallLineHeight
import br.com.nbagames.designsystem.theme.smallPadding
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
                modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = smallLineHeight,
                color = Color.White
            )
        }
    ) {
        repeat(2) { pageIndex ->
            Tab(
                text = {
                    Text(
                        text = if (pageIndex == 0) homeTeamName else visitorTeamName,
                        color = if (pagerState.currentPage == pageIndex) Color.White else Color.Gray,
                        textAlign = TextAlign.Center
                    )
                },
                selected = pagerState.currentPage == pageIndex,
                onClick = { scope.launch { pagerState.animateScrollToPage(pageIndex) } }
            )
        }
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
        modifier = modifier.padding(top = smallPadding),
        count = 2,
        state = pagerState,
        verticalAlignment = Alignment.Top,
        userScrollEnabled = false
    ) { page ->
        when (page) {
            0 -> BoxScoreTeamTab(
                playingPlayers = statistics.homePlayingPlayers,
                benchPlayers = statistics.homeBenchPlayers
            )
            1 -> BoxScoreTeamTab(
                playingPlayers = statistics.visitorPlayingPlayers,
                benchPlayers = statistics.visitorBenchPlayers
            )
        }
    }
}

@Composable
private fun BoxScoreTeamTab(
    modifier: Modifier = Modifier,
    playingPlayers: List<PlayerStatistics>,
    benchPlayers: List<PlayerStatistics>
) {
    val biggerPlayingName = getBiggerPlayerIdentification(playingPlayers)
    val biggerBenchName = getBiggerPlayerIdentification(benchPlayers)
    val biggerName = if (biggerBenchName.length >= biggerPlayingName.length) biggerBenchName else biggerPlayingName
    val density = LocalDensity.current
    val maxWidthDp = remember { mutableStateOf(80.dp) }
    val maxHeightDp = remember { mutableStateOf(45.dp) }

    Box {
        UnderlineText(
            text = biggerName,
            modifier = Modifier
                .requiredWidthIn(max = Dp.Infinity)
                .onSizeChanged {
                    maxWidthDp.value = with(density) { (it.width + 10).toDp() }
                    maxHeightDp.value = with(density) { max(maxHeightDp.value, it.height.toDp()) }
                }
                .alpha(0f)
        )

        Column(modifier = modifier) {
            StatisticsByPlayer(
                title = stringResource(id = R.string.starters),
                playersStatistics = playingPlayers,
                minCellHeight = maxHeightDp.value,
                minCellWidth = maxWidthDp.value
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(smallLineHeight)
                    .background(Color.Black)
            )
            StatisticsByPlayer(
                modifier = Modifier.padding(top = mediumPadding),
                title = stringResource(id = R.string.bench),
                playersStatistics = benchPlayers,
                minCellHeight = maxHeightDp.value,
                minCellWidth = maxWidthDp.value
            )
        }
    }
}

@Composable
fun StatisticsByPlayer(
    modifier: Modifier = Modifier,
    title: String = "",
    minCellHeight: Dp,
    minCellWidth: Dp,
    playersStatistics: List<PlayerStatistics>
) {
    Column(modifier = modifier) {
        val totalRows = playersStatistics.size + 1

        LazyHorizontalGrid(
            modifier = Modifier.height(minCellHeight * totalRows),
            rows = GridCells.Fixed(totalRows)
        ) {
            item {
                StatisticsHeader(
                    modifier = Modifier.background(MaterialTheme.colorScheme.primary),
                    minCellWidth = minCellWidth,
                    title = title
                )
            }

            items(playersStatistics.size) { index: Int ->
                val lineColor = if (index % 2 != 0) CustomColors.primary70 else Color.White
                PlayerStatisticsRow(
                    modifier = Modifier.background(lineColor),
                    minCellWidth = minCellWidth,
                    minCellHeight = minCellHeight,
                    statistics = playersStatistics[index]
                )
            }
        }
    }
}

@Composable
fun StatisticsHeader(
    modifier: Modifier = Modifier,
    minCellWidth: Dp = defaultCellSize,
    title: String
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        val headers = listOf(
            stringResource(id = R.string.points_abbreviation),
            stringResource(id = R.string.minutes_abbreviation),
            stringResource(id = R.string.fouls),
            stringResource(id = R.string.field_goals_abbreviation),
            stringResource(id = R.string.three_points_abbreviation),
            stringResource(id = R.string.free_throws_abbreviation),
            stringResource(id = R.string.rebounds),
            stringResource(id = R.string.assists),
            stringResource(id = R.string.steals),
            stringResource(id = R.string.turnover_abbreviation),
            stringResource(id = R.string.blocks)
        )

        UnderlineText(
            modifier = Modifier.size(width = minCellWidth, height = 60.dp),
            text = title,
            color = Color.White,
            textStyle = MaterialTheme.typography.titleMedium
        )
        headers.forEach { headerTitle ->
            UnderlineText(
                modifier = Modifier.size(width = min(80.dp, minCellWidth), height = 60.dp),
                text = headerTitle,
                color = Color.White,
                textStyle = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun PlayerStatisticsRow(
    modifier: Modifier = Modifier,
    minCellWidth: Dp,
    minCellHeight: Dp,
    statistics: PlayerStatistics
) {
    val statisticsList = listOf(
        statistics.points.toString(),
        statistics.minutesPlayed,
        statistics.personalFouls.toString(),
        stringResource(
            id = R.string.player_stats_pattern,
            statistics.fieldGoalsMade,
            statistics.fieldGoalsAttempted,
            statistics.fieldGoalsPercentage.toInt()
        ),
        stringResource(
            id = R.string.player_stats_pattern,
            statistics.threePointsMade,
            statistics.threePointsAttempted,
            statistics.threePointsPercentage.toInt()
        ),
        stringResource(
            id = R.string.player_stats_pattern,
            statistics.freeThrowsAttempted,
            statistics.freeThrowsMade,
            statistics.freeThrowsPercentage.toInt()
        ),
        stringResource(
            id = R.string.player_stats_pattern,
            statistics.offensiveRebound,
            statistics.defensiveRebounds,
            statistics.totalRebounds
        ),
        statistics.assists.toString(),
        statistics.steals.toString(),
        statistics.turnovers.toString(),
        statistics.blocks.toString()
    )

    Row {
        UnderlineText(
            modifier = modifier
                .size(width = minCellWidth, height = minCellHeight)
                .padding(start = smallPadding),
            text = statistics.player.getCompletePlayerIdentification(),
            contentAlign = Alignment.CenterStart
        )
        statisticsList.forEach { contentText: String ->
            UnderlineText(
                modifier = modifier.size(width = min(80.dp, minCellWidth), height = minCellHeight),
                text = contentText
            )
        }
    }
}

@Composable
fun UnderlineText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Black,
    contentAlign: Alignment = Alignment.Center,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium
) {
    Box(modifier, contentAlignment = contentAlign) {
        Text(
            text = text,
            color = color,
            textAlign = TextAlign.Center,
            style = textStyle
        )
    }
}

private fun getBiggerPlayerIdentification(playersStatistics: List<PlayerStatistics>): String {
    val biggerPlayerStatistic = playersStatistics.maxByOrNull { it.player.getCompletePlayerIdentification().length }
    return biggerPlayerStatistic?.player?.getCompletePlayerIdentification() ?: ""
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
            fieldGoalsPercentage = 80.0,
            freeThrowsAttempted = 0,
            freeThrowsMade = 0,
            freeThrowsPercentage = 90.0,
            threePointsAttempted = 0,
            threePointsMade = 0,
            threePointsPercentage = 80.0,
            offensiveRebound = 0,
            defensiveRebounds = 0,
            totalRebounds = 0
        )
    )

    val gameStatistics = GameStatistics(
        homePlayingPlayers = playersStatistics,
        homeBenchPlayers = playersStatistics,
        visitorPlayingPlayers = playersStatistics,
        visitorBenchPlayers = playersStatistics
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
