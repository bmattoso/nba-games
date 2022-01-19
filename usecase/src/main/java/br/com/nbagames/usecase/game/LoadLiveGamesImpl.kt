package br.com.nbagames.usecase.game

import br.com.nbagames.model.Game
import br.com.nbagames.repository.game.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class LoadLiveGamesImpl(private val gameRepository: GameRepository) : LoadLiveGames {

    override suspend operator fun invoke(): Flow<List<Game>> = flowOf(gameRepository.getLiveGamesFromRemote())
}
