package br.com.nbagames.remote.game.fixture

import br.com.nbagames.remote.game.response.ScoreResponse
import kotlin.random.Random

object ScoreResponseFixture {
    fun get() = ScoreResponse(
        points = Random(seed = 100).nextInt(),
        linescore = listOf("10", "20", "30", "40")
    )
}
