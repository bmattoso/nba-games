package br.com.nbagames.remote.game.response

import kotlinx.serialization.Serializable

@Serializable
data class ScoreResponse(
    val points: Int,
    val linescore: List<String>
)
