package br.com.nbagames.game.presentation

import androidx.lifecycle.ViewModel
import br.com.nbagames.model.LiveGame
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LiveGameViewModel : ViewModel() {

    fun getLiveGameList(): Flow<LiveGameViewState> = flow {
        val games = mutableListOf<LiveGame>()
        repeat(4) {
            games.add(
                LiveGame(
                    id = "1",
                    homeTeam = "Miami Heat",
                    awayTeam = "Brooklyn Nets",
                    homePoints = 10,
                    awayPoints = 11
                )
            )
        }

        emit(
            LiveGameViewState.Loaded(games)
        )
    }
}
