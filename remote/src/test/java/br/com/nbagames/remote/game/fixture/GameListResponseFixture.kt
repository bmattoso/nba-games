package br.com.nbagames.remote.game.fixture

import br.com.nbagames.remote.game.response.GameListResponse

object GameListResponseFixture {

    fun get() = GameListResponse(
        gameList = GameResponseFixture.getList()
    )

    fun getEmptyList() = GameListResponse(
        gameList = emptyList()
    )
}
