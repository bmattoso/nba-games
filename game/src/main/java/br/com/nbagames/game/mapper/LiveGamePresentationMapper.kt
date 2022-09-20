package br.com.nbagames.game.mapper

import br.com.nbagames.game.presentation.GamePresentation
import br.com.nbagames.model.Game

class LiveGamePresentationMapper {

    fun mapLiveGamePresentation(gameList: List<Game>): List<GamePresentation> {
        return gameList.map { liveGame ->
            GamePresentation(
                id = liveGame.id,
                homeTeam = liveGame.homeTeam,
                visitantTeam = liveGame.visitorTeam,
                homePoints = liveGame.homePoints,
                visitantPoints = liveGame.visitorPoints,
                gameClock = liveGame.currentClock,
                quarter = liveGame.quarter
            )
        }
    }
}
