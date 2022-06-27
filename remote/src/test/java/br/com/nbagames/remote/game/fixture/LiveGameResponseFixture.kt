package br.com.nbagames.remote.game.fixture

import br.com.nbagames.remote.game.response.LiveGameResponse

object LiveGameResponseFixture {

    fun get() = LiveGameResponse(
        liveGameApiResponse = GameListResponseFixture.get()
    )
}
