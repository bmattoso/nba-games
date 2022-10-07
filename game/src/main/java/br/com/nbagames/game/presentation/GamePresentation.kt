package br.com.nbagames.game.presentation

import br.com.nbagames.model.GameStatus
import br.com.nbagames.model.Quarter
import br.com.nbagames.model.Team

data class GamePresentation(
    val id: Int,
    val homeTeam: Team,
    val visitantTeam: Team,
    val homePoints: Int,
    val visitantPoints: Int,
    val gameClock: String?,
    val quarter: Quarter,
    val status: GameStatus,
    val startTime: String?
)
