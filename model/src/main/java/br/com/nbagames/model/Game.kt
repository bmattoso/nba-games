package br.com.nbagames.model

import br.com.nbagames.model.statistics.GameStatistics

data class Game(
    val id: Int,
    val homeTeam: Team,
    val visitorTeam: Team,
    val homePoints: Int,
    val visitorPoints: Int,
    val currentClock: String?,
    val gameStatus: GameStatus,
    val quarter: Quarter,
    val officials: List<Official>,
    val quarterScoreHistory: QuarterScoreHistory?,
    val gameStatistics: GameStatistics?
)
