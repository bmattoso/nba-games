package br.com.nbagames.game.presentation.detail

import br.com.nbagames.game.presentation.GamePresentation
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
    val isGameFinished: Boolean,
    val isHalftime: Boolean,
    val quarterScoreHistory: QuarterScoreHistory?,
    val gameStatistics: GameStatistics?
) {

    fun toGamePresentation() = GamePresentation(
        id = this.id,
        homeTeam = this.homeTeam,
        visitantTeam = this.visitantTeam,
        homePoints = this.homePoints,
        visitantPoints = this.visitantPoints,
        gameClock = this.gameClock,
        quarter = this.quarter
    )
}
