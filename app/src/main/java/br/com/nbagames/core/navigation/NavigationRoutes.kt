package br.com.nbagames.core.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
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
import br.com.nbagames.game.view.GameDetail
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
            route = Route.GameDetail.route,
            arguments = listOf(navArgument(GAME_ID) { type = NavType.IntType })
        ) {
            val gameId = it.arguments?.getInt(GAME_ID) ?: -1
            GameDetail(
                modifier = Modifier.fillMaxSize(),
                gameId = gameId,
                onPlayerClick = { playerId -> actions.onPlayerClick(playerId) },
                onTeamClick = { teamId -> actions.onTeamClick(teamId) }
            )
        }

        composable(HomeRoute.LiveGame.route) {
            LiveGameList(
                modifier = Modifier.fillMaxSize(),
                onLiveGameClick = actions::onLiveGameClick,
                onClickOpenCalendar = actions::onClickOpenCalendar
            )
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

        composable(
            route = Route.TeamDetail.route,
            arguments = listOf(navArgument(TEAM_ID) { type = NavType.IntType })
        ) {
            val teamId = it.arguments?.getInt(TEAM_ID) ?: -1
        }

        composable(
            route = Route.PlayerDetail.route,
            arguments = listOf(navArgument(PLAYER_ID) { type = NavType.IntType })
        ) {
            val playerId = it.arguments?.getInt(PLAYER_ID) ?: -1
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

    fun onLiveGameClick(gameId: Int) {
        navController.navigate(Route.GameDetail.route.replace("{$GAME_ID}", gameId.toString()))
    }

    fun onClickOpenCalendar() {
        navController.navigate(HomeRoute.Calendar.route)
    }

    fun onPlayerClick(playerId: Int) {
        navController.navigate(Route.PlayerDetail.route.replace("{$PLAYER_ID}", playerId.toString()))
    }

    fun onTeamClick(teamId: Int) {
        navController.navigate(Route.TeamDetail.route.replace("{$TEAM_ID}", teamId.toString()))
    }
}
