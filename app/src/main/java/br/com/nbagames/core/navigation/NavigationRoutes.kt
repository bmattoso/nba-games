package br.com.nbagames.core.navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.nbagames.designsystem.color.CustomColors.blackCurrant
import br.com.nbagames.designsystem.components.TextField
import br.com.nbagames.game.view.LiveGameDetail
import br.com.nbagames.game.view.LiveGameList
import br.com.nbagames.splash.Splash

@Composable
fun NavigationRoutes(
    navController: NavHostController,
    startDestination: String = Route.Splash.route,
    onContentLoaded: () -> Unit
) {
    val actions = remember(navController) { NavigationDestination(navController) }

    NavHost(navController = navController, startDestination = startDestination) {

        composable(Route.Splash.route) {
            Splash(
                modifier = Modifier.background(blackCurrant),
                onSetupLoaded = { actions.onSetupLoaded(onContentLoaded) }
            )
        }

        composable(
            route = Route.LiveGameDetail.route,
            arguments = listOf(navArgument("gameId") { type = NavType.StringType }),
        ) {
            LiveGameDetail()
        }

        composable(HomeRoute.LiveGame.route) {
            LiveGameList(onLiveGameClick = actions::onLiveGameClick)
        }

        composable(HomeRoute.Standings.route) {
            TextField(text = stringResource(id = HomeRoute.Standings.tabName))
        }
        composable(HomeRoute.Teams.route) {
            TextField(text = stringResource(id = HomeRoute.Teams.tabName))
        }
        composable(HomeRoute.Calendar.route) {
            TextField(text = stringResource(id = HomeRoute.Calendar.tabName))
        }
    }
}

internal class NavigationDestination(private val navController: NavHostController) {
    fun onSetupLoaded(onContentLoad: () -> Unit) {
        navController.navigate(HomeRoute.LiveGame.route) {
            popUpTo(Route.Splash.route) {
                inclusive = true
            }
            onContentLoad()
        }
    }

    fun onLiveGameClick(gameId: String) {
        navController.navigate("liveGame/$gameId")
    }
}
