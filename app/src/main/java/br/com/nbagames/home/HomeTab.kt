package br.com.nbagames.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import br.com.nbagames.R

enum class HomeTab(
    @StringRes val tabName: Int,
    @DrawableRes val tabIcon: Int,
) {
    LiveGame(R.string.live_games, R.drawable.ic_ball),
    Standing(R.string.standings, R.drawable.ic_standing),
    Teams(R.string.teams, R.drawable.ic_teams),
    Calendar(R.string.calendar, R.drawable.ic_calendar),
}
