package br.com.nbagames.usecase.game

import br.com.nbagames.model.Game
import br.com.nbagames.repository.game.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoadLiveGamesImpl(private val gameRepository: GameRepository) : LoadLiveGames {

    override suspend operator fun invoke() = flow<List<Game>> { gameRepository.getLiveGamesFromRemote() }
        .catch { throwable -> throw throwable }
        .flowOn(Dispatchers.IO)
}
