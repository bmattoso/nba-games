package br.com.nbagames.usecase.game

import br.com.nbagames.model.LiveGame
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class LoadLiveGamesImpl : LoadLiveGames {

    override suspend operator fun invoke(): Flow<List<LiveGame>> {
        return flowOf(emptyList())
    }
}
