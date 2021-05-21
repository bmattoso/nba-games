package br.com.nbagames.game.presentation

import androidx.lifecycle.ViewModel
import br.com.nbagames.model.LiveGame
import br.com.nbagames.model.Team
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LiveGameViewModel : ViewModel() {

    fun getLiveGameList(): Flow<LiveGameViewState> = flow {
        val games = mutableListOf<LiveGame>()
        repeat(4) {
            games.add(
                LiveGame(
                    id = "1",
                    homeTeam = Team(
                        id = "1",
                        fullName = "Miami Heat",
                        nickName = "Heat",
                        shortName = "MHT",
                        logo = ""
                    ),
                    awayTeam = Team(
                        id = "2",
                        fullName = "Brooklyn Nets",
                        nickName = "Nets",
                        shortName = "BNT",
                        logo = ""
                    ),
                    homePoints = 10,
                    awayPoints = 11,
                    currentTime = "2:50"
                )
            )
        }

        emit(
            LiveGameViewState.Loaded(games)
        )
    }
}
