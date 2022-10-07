package br.com.nbagames.remote.game.response

import br.com.nbagames.remote.common.ClockSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GameStatusResponse(
    @Serializable(with = ClockSerializer::class) val clock: String?,
    val halftime: Boolean,
    @SerialName("short") val statusCode: Int,
    @SerialName("long") val description: String
)
