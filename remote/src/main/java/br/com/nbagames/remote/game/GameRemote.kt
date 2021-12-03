package br.com.nbagames.remote.game

import br.com.nbagames.model.Game

interface GameRemote {
    suspend fun getLiveGameList(): List<Game>
}
