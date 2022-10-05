package br.com.nbagames.model.statistics

data class GameStatistics(
    val homePlayingPlayers: List<PlayerStatistics>,
    val homeBenchPlayers: List<PlayerStatistics>,
    val visitorPlayingPlayers: List<PlayerStatistics>,
    val visitorBenchPlayers: List<PlayerStatistics>
)
