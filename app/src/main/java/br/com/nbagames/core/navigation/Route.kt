package br.com.nbagames.core.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import br.com.nbagames.R

const val GAME_ID = "gameId"
const val PLAYER_ID = "playerId"
const val TEAM_ID = "teamId"

sealed class Route(val route: String) {
    object Splash : Route("splash")
    object GameDetail : Route("game/{$GAME_ID}")
    object PlayerDetail : Route("player/{$PLAYER_ID}")
    object TeamDetail : Route("team/{$TEAM_ID}")
}

sealed class HomeRoute(
    homeRoute: String,
    @StringRes val tabName: Int,
    @DrawableRes val tabIcon: Int
) : Route(route = homeRoute) {
    object LiveGame : HomeRoute("liveGame", R.string.live_game_list, R.drawable.ic_ball)
    object Standings : HomeRoute("standings", R.string.standings, R.drawable.ic_standing)
    object Teams : HomeRoute("team", R.string.teams, R.drawable.ic_teams)
    object Calendar : HomeRoute("calendar", R.string.calendar, R.drawable.ic_calendar)
}

fun String.toRouteOrNull(): Route? = when (this) {
    HomeRoute.LiveGame.route -> HomeRoute.LiveGame
    HomeRoute.Standings.route -> HomeRoute.Standings
    HomeRoute.Teams.route -> HomeRoute.Teams
    HomeRoute.Calendar.route -> HomeRoute.Calendar
    Route.GameDetail.route -> Route.GameDetail
    Route.TeamDetail.route -> Route.TeamDetail
    Route.PlayerDetail.route -> Route.PlayerDetail
    else -> null
}
