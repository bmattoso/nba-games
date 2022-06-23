package br.com.nbagames.game.mapper

import br.com.nbagames.game.view.extension.getResourceTextId
import br.com.nbagames.model.Game
import br.com.nbagames.game.presentation.LiveGamePresentation

class LiveGamePresentationMapper {

    fun mapLiveGamePresentation(gameList: List<Game>): List<LiveGamePresentation> {
        return gameList.map { liveGame ->
            LiveGamePresentation(
                id = liveGame.id,
                homeTeam = liveGame.homeTeam,
                visitantTeam = liveGame.visitorTeam,
                homePoints = liveGame.homePoints,
                visitantPoints = liveGame.visitorPoints,
                gameClock = liveGame.currentClock,
                quarter = liveGame.quarter.getResourceTextId()
            )
        }
    }
}
