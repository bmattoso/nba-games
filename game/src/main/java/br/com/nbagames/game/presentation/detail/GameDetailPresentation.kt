package br.com.nbagames.game.presentation.detail

import br.com.nbagames.game.presentation.GamePresentation
import br.com.nbagames.model.GameStatus
import br.com.nbagames.model.Official
import br.com.nbagames.model.Quarter
import br.com.nbagames.model.QuarterScoreHistory
import br.com.nbagames.model.Team
import br.com.nbagames.model.statistics.GameStatistics

data class GameDetailPresentation(
    val id: Int,
    val homeTeam: Team,
    val visitantTeam: Team,
    val homePoints: Int,
    val visitantPoints: Int,
    val gameClock: String?,
    val quarter: Quarter,
    val status: GameStatus,
    val startTime: String?,
    val isGameFinished: Boolean,
    val isHalftime: Boolean,
    val quarterScoreHistory: QuarterScoreHistory?,
    val gameStatistics: GameStatistics?,
    val officials: List<Official>
) {

    fun toGamePresentation() = GamePresentation(
        id = id,
        homeTeam = homeTeam,
        visitantTeam = visitantTeam,
        homePoints = homePoints,
        visitantPoints = visitantPoints,
        gameClock = gameClock,
        quarter = quarter,
        status = status,
        startTime = startTime
    )
}
