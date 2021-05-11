package br.com.nbagames.home

import androidx.compose.animation.Crossfade
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import br.com.nbagames.designsystem.components.TextField
import br.com.nbagames.game.view.LiveGameList

@Composable
fun Home(
    onLiveGameClick: (gameId: String) -> Unit
) {
    val (currentTab, selectTab) = rememberSaveable { mutableStateOf(HomeTab.LiveGame) }

    Crossfade(currentTab) { currentHomeTab ->
        Scaffold(
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
fun HomeContent(
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
