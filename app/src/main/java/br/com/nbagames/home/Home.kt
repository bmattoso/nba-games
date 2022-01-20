package br.com.nbagames.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import br.com.nbagames.core.navigation.HomeRoute
import br.com.nbagames.core.navigation.NavigationRoutes
import br.com.nbagames.core.navigation.Route
import br.com.nbagames.core.navigation.toRouteOrNull
import br.com.nbagames.designsystem.theme.NbaGamesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home() {
    val navController = rememberNavController()
    val currentTab by navController.currentRouteAsState()
    var isContentLoaded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            if (isContentLoaded) {
                HomeTopBar(currentNavigationRoute = currentTab)
            }
        },
        bottomBar = {
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
    ) { innerPadding ->
        Surface(Modifier.padding(innerPadding)) {
            NavigationRoutes(
                navController = navController,
                onContentLoaded = { isContentLoaded = true }
            )
        }
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
    CenterAlignedTopAppBar(
        title = {
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = stringResource(currentNavigationRoute.title)
            )
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    )
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
