package br.com.nbagames.usecase.game.presentation

import br.com.nbagames.model.Team

data class LiveGamePresentation(
    val id: String,
    val homeTeam: Team,
    val visitantTeam: Team,
    val homePoints: Int,
    val visitantPoints: Int,
    val gameClock: String,
    val quarter: Int
)
