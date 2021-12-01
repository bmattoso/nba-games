package br.com.nbagames.usecase.game.presentation

import br.com.nbagames.model.Team

data class LiveGamePresentation(
    val id: String,
    val homeTeam: Team,
    val awayTeam: Team,
    val homePoints: Int,
    val awayPoints: Int,
    val currentTime: String
)
