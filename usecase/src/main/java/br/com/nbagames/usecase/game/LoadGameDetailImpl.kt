package br.com.nbagames.usecase.game

import br.com.nbagames.model.Game
import br.com.nbagames.repository.game.GameRepository
import br.com.nbagames.repository.official.OfficialRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class LoadGameDetailImpl(
    private val gameRepository: GameRepository,
    private val officialRepository: OfficialRepository
) : LoadGameDetail {

    override suspend operator fun invoke(gameId: Int): Flow<Game> {
        return flow {
            val game = gameRepository.getGameDetailById(gameId)

            val gameStatistics = try {
                gameRepository.getGameStatistics(gameId)
            } catch (ex: IllegalStateException) {
                null
            }

            val officials = game.officials.map { official -> officialRepository.getOfficialImageByName(official.id) }
            emit(game.copy(gameStatistics = gameStatistics, officials = officials))
        }.catch { throwable -> throw throwable }
    }
}
