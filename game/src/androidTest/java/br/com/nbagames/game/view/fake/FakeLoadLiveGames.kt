package br.com.nbagames.game.view.fake

import br.com.nbagames.model.Game
import br.com.nbagames.usecase.game.LoadLiveGames
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class FakeLoadLiveGames : LoadLiveGames {

    var throwable: Exception? = null
    var gameList: MutableList<Game> = mutableListOf()

    override suspend fun invoke(): Flow<List<Game>> {
        return flow {
            throwable?.let { throwable -> throw throwable }
            emit(gameList)
        }.catch { ex -> throw ex }
    }
}
