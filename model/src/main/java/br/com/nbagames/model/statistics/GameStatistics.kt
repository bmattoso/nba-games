package br.com.nbagames.model.statistics

data class GameStatistics(
    val homePlayersStatistics: List<PlayerStatistics>,
    val visitorPlayersStatistics: List<PlayerStatistics>
)
