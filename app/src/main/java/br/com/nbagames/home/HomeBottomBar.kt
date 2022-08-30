package br.com.nbagames.home

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.nbagames.core.navigation.HomeRoute
import br.com.nbagames.core.navigation.Route

@Composable
fun HomeBottomBar(
    isContentLoaded: Boolean,
    currentTab: Route,
    onTabSelected: (HomeRoute) -> Unit
) {
    if (isContentLoaded) {
        NavigationBar {
            homeBottomBarOptions.forEach { homeTab ->
                val navigationTab = getTabFromRelatedNavigation(currentTab)
                val isSelected = homeTab == navigationTab

                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(homeTab.tabIcon),
                            contentDescription = stringResource(id = currentTab.title),
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    label = { Text(text = stringResource(homeTab.tabName)) },
                    selected = isSelected,
                    onClick = { onTabSelected(homeTab) }
                )
            }
        }
    }
}

private fun getTabFromRelatedNavigation(currentTab: Route): HomeRoute {
    if (homeBottomBarOptions.contains(currentTab)) {
        return currentTab as HomeRoute
    }

    return when (currentTab) {
        Route.GameDetail -> HomeRoute.LiveGame
        else -> HomeRoute.LiveGame
    }
}

private val homeBottomBarOptions = listOf(
    HomeRoute.LiveGame,
    HomeRoute.Standings,
    HomeRoute.Teams,
    HomeRoute.Calendar
)
