package br.com.nbagames.game.view

import androidx.annotation.DrawableRes
import br.com.nbagames.game.R

enum class LiveGameCardBackground(
    @DrawableRes val backgroundRes: Int
) {
    BallAndTable(R.drawable.ball_and_table_dark),
    FireBall(R.drawable.fireball_dark),
    Arena(R.drawable.arena_dark),
    Panel(R.drawable.panel_dark)
}