package br.com.nbagames.remote.game.fixture

import br.com.nbagames.remote.game.response.GameScoreResponse

object GameScoreResponseFixture {

    fun get() = GameScoreResponse(
        visitors = ScoreResponseFixture.get(),
        home = ScoreResponseFixture.get()
    )
}
