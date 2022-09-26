package br.com.nbagames.remote.statistics

@kotlinx.serialization.Serializable
data class GameStatisticsResponse(
    val statisticsPlayerResponseList: List<StatisticsPlayerResponse>
)
