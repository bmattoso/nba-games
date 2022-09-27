package br.com.nbagames.game.presentation.detail

data class GameStatisticsPresentation(
    val homePlayersStatistics: List<PlayerStatisticsPresentation>,
    val visitorPlayersStatistics: List<PlayerStatisticsPresentation>
)
