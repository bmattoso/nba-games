package br.com.nbagames.game.presentation

import androidx.lifecycle.ViewModel
import br.com.nbagames.model.LiveGame
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LiveGameViewModel : ViewModel() {

    fun getLiveGameList(): Flow<LiveGameViewState> = flow {
        emit(
            LiveGameViewState.Loaded(
                listOf(
                    LiveGame(
                        id = "1",
                        homeTeam = "Miami Heat",
                        awayTeam = "Brooklyn Nets",
                        homePoints = 10,
                        awayPoints = 11
                    )
                )
            )
        )
    }
}
