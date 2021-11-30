package br.com.nbagames.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import br.com.nbagames.core.navigation.HomeRoute
import br.com.nbagames.core.navigation.NavigationRoutes
import br.com.nbagames.core.navigation.Route
import br.com.nbagames.core.navigation.toRouteOrNull
import br.com.nbagames.designsystem.theme.NbaGamesTheme
import br.com.nbagames.game.R

@Composable
fun Home() {
    val navController = rememberNavController()
    var isContentLoaded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            val currentTab by navController.currentRouteAsState()
            if (isContentLoaded) {
                HomeTopBar(currentNavigationRoute = currentTab)
            }
        },
        bottomBar = {
            val currentTab by navController.currentRouteAsState()
            HomeBottomBar(
                isContentLoaded = isContentLoaded,
                currentTab = currentTab,
                onTabSelected = { selectedTab ->
                    if (selectedTab != currentTab) {
                        onChangeTab(navController, selectedTab.route)
                    }
                }
            )
        }
    ) {
        NavigationRoutes(
            navController = navController,
            onContentLoaded = { isContentLoaded = true }
        )
    }
}

@Stable
@Composable
private fun NavController.currentRouteAsState(): State<Route> {
    val navigationState = remember { mutableStateOf<Route>(HomeRoute.LiveGame) }

    DisposableEffect(Unit) {
        val callback = NavController.OnDestinationChangedListener { _, destination, _ ->
            destination.route?.let { destinationRoute ->
                destinationRoute.toRouteOrNull()?.let { navigationState.value = it }
            }
        }
        addOnDestinationChangedListener(callback)

        onDispose {
            removeOnDestinationChangedListener(callback)
        }
    }

    return navigationState
}

@Composable
private fun HomeTopBar(
    currentNavigationRoute: Route
) {
    TopAppBar(backgroundColor = colorResource(id = R.color.blackCurrant), elevation = 4.dp) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.h5,
                text = stringResource(currentNavigationRoute.title)
            )
        }
    }
}

private fun onChangeTab(navController: NavController, destinationTab: String) {
    navController.navigate(destinationTab) {
        launchSingleTop = true
        restoreState = true

        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    NbaGamesTheme {
        Home()
    }
}
