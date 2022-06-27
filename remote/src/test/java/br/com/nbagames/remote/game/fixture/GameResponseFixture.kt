package br.com.nbagames.remote.game.fixture

import br.com.nbagames.remote.game.response.GameResponse
import kotlin.random.Random

object GameResponseFixture {

    fun get(
        id: Int = Random(seed = 50).nextInt()
    ) = GameResponse(
        id = id,
        status = GameStatusResponseFixture.get(),
        periods = GamePeriodsResponseFixture.get(),
        teams = GameTeamsResponseFixture.get(),
        scores = GameScoreResponseFixture.get()
    )

    fun getList(totalGames: Int = 5): List<GameResponse> {
        val games = mutableListOf<GameResponse>()
        repeat(totalGames) { index -> games.add(get(id = index)) }
        return games
    }
}
