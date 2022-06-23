package br.com.nbagames.remote.game.response

import br.com.nbagames.remote.team.response.TeamResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GameTeamsResponse(
    @SerialName("home") val home: TeamResponse,
    @SerialName("visitors") val visitor: TeamResponse
)
