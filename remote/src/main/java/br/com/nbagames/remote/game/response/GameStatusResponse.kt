package br.com.nbagames.remote.game.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GameStatusResponse(
    val clock: String?,
    val halftime: Boolean,
    @SerialName("long") val description: String
)
