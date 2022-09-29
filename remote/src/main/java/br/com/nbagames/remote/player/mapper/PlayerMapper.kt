package br.com.nbagames.remote.player.mapper

import br.com.nbagames.model.player.Player
import br.com.nbagames.remote.player.PlayerResponse

class PlayerMapper {

    fun mapPlayer(playerResponse: PlayerResponse) = Player(
        id = playerResponse.id,
        firstName = playerResponse.firstName,
        lastName = playerResponse.lastName,
        position = null
    )
}
