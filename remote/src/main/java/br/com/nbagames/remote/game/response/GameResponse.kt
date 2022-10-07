package br.com.nbagames.remote.game.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameResponse(
    val id: Int,
    @SerialName("date") val scheduleDate: GameDateResponse,
    val status: GameStatusResponse,
    val periods: GamePeriodsResponse,
    val teams: GameTeamsResponse,
    val scores: GameScoreResponse,
    val officials: List<String>
)
