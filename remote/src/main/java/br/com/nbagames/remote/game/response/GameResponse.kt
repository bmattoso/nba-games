package br.com.nbagames.remote.game.response

import kotlinx.serialization.Serializable

@Serializable
data class GameResponse(
    val id: Int,
    val status: GameStatusResponse,
    val periods: GamePeriodsResponse,
    val teams: GameTeamsResponse,
    val scores: GameScoreResponse,
    val officials: List<String>
)
