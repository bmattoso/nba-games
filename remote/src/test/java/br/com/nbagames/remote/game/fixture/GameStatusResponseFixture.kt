package br.com.nbagames.remote.game.fixture

import br.com.nbagames.remote.game.response.GameStatusResponse

object GameStatusResponseFixture {

    fun get() = GameStatusResponse(
        clock = "04:21",
        halftime = false,
        description = "Running"
    )
}
