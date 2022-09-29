package br.com.nbagames.usecase.game

import br.com.nbagames.model.Game
import kotlinx.coroutines.flow.Flow

interface LoadGameDetail {
    suspend operator fun invoke(gameId: Int): Flow<Game>
}
