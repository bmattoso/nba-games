package br.com.nbagames.usecase.game

import br.com.nbagames.model.Game
import br.com.nbagames.repository.game.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class LoadGameDetailImpl(private val gameRepository: GameRepository) : LoadGameDetail {

    override suspend operator fun invoke(gameId: Int): Flow<Game> {
        return flow {
            val game = gameRepository.getGameDetailById(gameId)
            val gameStatistics = gameRepository.getGameStatistics(gameId)

            emit(game.copy(gameStatistics = gameStatistics))
        }.catch { throwable -> throw throwable }
    }
}
