package br.com.nbagames.usecase.game

import br.com.nbagames.model.Game
import kotlinx.coroutines.flow.Flow

interface LoadLiveGames {
    suspend operator fun invoke(): Flow<List<Game>>
}
