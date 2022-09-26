package br.com.nbagames.game.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import br.com.nbagames.designsystem.color.CustomColors
import br.com.nbagames.designsystem.theme.NbaGamesTheme
import br.com.nbagames.designsystem.theme.mediumPadding
import br.com.nbagames.designsystem.theme.smallLineHeight
import br.com.nbagames.game.R
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
            pagerState = pagerState
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
                    color = if (pagerState.currentPage == 0) Color.White else Color.LightGray,
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
                    color = if (pagerState.currentPage == 1) Color.White else Color.LightGray,
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
    pagerState: PagerState
) {
    HorizontalPager(
        modifier = modifier,
        count = 2,
        state = pagerState
    ) { page ->
        when (page) {
            0 -> Text("Welcome to Home Screen")
            1 -> Text("Welcome to Shopping Screen")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameBoxScorePreview() {
    NbaGamesTheme {
        GameBoxScore(
            modifier = Modifier.padding(mediumPadding),
            homeTeamName = "Miami Heat",
            visitorTeamName = "Golden State Warriors",
            onPlayerClick = {}
        )
    }
}
