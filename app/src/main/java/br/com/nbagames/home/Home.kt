package br.com.nbagames.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.nbagames.designsystem.components.TextField
import br.com.nbagames.game.R
import br.com.nbagames.game.view.LiveGameList

@Composable
fun Home(
    onLiveGameClick: (gameId: String) -> Unit
) {
    val (currentTab, selectTab) = rememberSaveable { mutableStateOf(HomeTab.LiveGame) }

    Crossfade(currentTab) { currentHomeTab ->
        Scaffold(
            topBar = {
                HomeTopBar(currentHomeTab = currentHomeTab)
            },
            content = {
                HomeContent(
                    currentHomeTab = currentHomeTab,
                    onLiveGameClick = onLiveGameClick
                )
            },
            bottomBar = {
                HomeBottomBar(
                    currentTab = currentHomeTab,
                    onTabSelected = selectTab
                )
            }
        )
    }
}

@Composable
private fun HomeTopBar(
    currentHomeTab: HomeTab
) {
    TopAppBar(backgroundColor = colorResource(id = R.color.blackCurrant), elevation = 4.dp) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.h5,
                text = stringResource(currentHomeTab.tabName)
            )
        }
    }
}

@Composable
private fun HomeContent(
    currentHomeTab: HomeTab,
    onLiveGameClick: (gameId: String) -> Unit
) {
    val tabName = stringResource(id = currentHomeTab.tabName)
    when (currentHomeTab) {
        HomeTab.LiveGame -> LiveGameList(onLiveGameClick)
        HomeTab.Standing -> TextField(text = tabName)
        HomeTab.Teams -> TextField(text = tabName)
        HomeTab.Calendar -> TextField(text = tabName)
    }
}
