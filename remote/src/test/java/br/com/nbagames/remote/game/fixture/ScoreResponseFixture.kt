package br.com.nbagames.remote.game.fixture

import br.com.nbagames.remote.game.response.ScoreResponse
import kotlin.random.Random

object ScoreResponseFixture {
    fun get() = ScoreResponse(Random(seed = 100).nextInt())
}
