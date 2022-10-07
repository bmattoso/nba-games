package br.com.nbagames.remote.game.fixture

import br.com.nbagames.remote.game.response.GameDateResponse

object GameDateResponseFixture {

    fun get(
        date: String = "2022-06-09T01:00:00.000Z"
    ) = GameDateResponse(
        start = date
    )
}
