package br.com.nbagames.remote.game.response

import kotlinx.serialization.Serializable

@Serializable
class GamePeriodsResponse(
    val current: Int,
    val endOfPeriod: Boolean
)
