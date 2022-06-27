package br.com.nbagames.remote.game.fixture

import br.com.nbagames.remote.game.response.GamePeriodsResponse

object GamePeriodsResponseFixture {

    fun get() = GamePeriodsResponse(
        current = 3,
        endOfPeriod = false
    )
}
