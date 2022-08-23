package br.com.nbagames.core.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import br.com.nbagames.R

sealed class Route(val route: String, @StringRes val title: Int = 0) {
    object Splash : Route("splash")
    object LiveGameDetail : Route("liveGame/{gameId}", R.string.game_details)
}

sealed class HomeRoute(
    homeRoute: String,
    @StringRes val tabName: Int,
    @DrawableRes val tabIcon: Int
) : Route(route = homeRoute, title = tabName) {
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
    Route.LiveGameDetail.route -> Route.LiveGameDetail
    else -> null
}
