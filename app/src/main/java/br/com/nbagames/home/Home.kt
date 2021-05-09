package br.com.nbagames.home

import androidx.compose.animation.Crossfade
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import br.com.nbagames.designsystem.components.TextField

@Composable
fun Home() {
    val (currentTab, selectTab) = rememberSaveable { mutableStateOf(HomeTab.LiveGame) }

    Crossfade(currentTab) { currentHomeTab ->
        Scaffold(
            content = {
                HomeContent(currentHomeTab)
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
    currentHomeTab: HomeTab
) {
    when (currentHomeTab) {
        HomeTab.LiveGame -> TextField(text = "Live Games")
        HomeTab.Standing -> TextField(text = "Standing")
        HomeTab.Teams -> TextField(text = "Teams")
        HomeTab.Calendar -> TextField(text = "Calendar")
    }
}
