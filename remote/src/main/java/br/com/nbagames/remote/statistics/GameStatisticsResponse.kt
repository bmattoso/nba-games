package br.com.nbagames.remote.statistics

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class GameStatisticsResponse(
    @SerialName("response") val playerStatisticsResponseList: List<PlayerStatisticsResponse>
)
