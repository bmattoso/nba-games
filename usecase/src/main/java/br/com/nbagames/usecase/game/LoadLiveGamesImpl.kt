package br.com.nbagames.usecase.game

import br.com.nbagames.repository.game.GameRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class LoadLiveGamesImpl(private val gameRepository: GameRepository) : LoadLiveGames {

    override suspend operator fun invoke() = flow { emit(gameRepository.getLiveGamesFromRemote()) }
        .catch { throwable -> throw throwable }
}
