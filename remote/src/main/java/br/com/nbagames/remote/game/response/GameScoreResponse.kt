package br.com.nbagames.remote.game.response

import kotlinx.serialization.Serializable

@Serializable
class GameScoreResponse(
    val visitors: ScoreResponse,
    val home: ScoreResponse
)
