package br.com.nbagames.game.mapper

import br.com.nbagames.designsystem.extension.getResourceTextId
import br.com.nbagames.model.Game
import br.com.nbagames.usecase.game.presentation.LiveGamePresentation

class LiveGamePresentationMapper {

    fun mapLiveGamePresentation(gameList: List<Game>): List<LiveGamePresentation> {
        return gameList.map { liveGame ->
            LiveGamePresentation(
                id = liveGame.id,
                homeTeam = liveGame.homeTeam,
                visitantTeam = liveGame.visitantTeam,
                homePoints = liveGame.homePoints,
                visitantPoints = liveGame.visitantPoints,
                gameClock = liveGame.currentClock,
                quarter = liveGame.quarter.getResourceTextId()
            )
        }
    }
}
