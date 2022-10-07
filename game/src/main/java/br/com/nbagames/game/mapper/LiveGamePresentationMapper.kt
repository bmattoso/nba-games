package br.com.nbagames.game.mapper

import br.com.nbagames.core.extension.formatDateHour
import br.com.nbagames.game.presentation.GamePresentation
import br.com.nbagames.model.Game

class LiveGamePresentationMapper {

    fun mapLiveGamePresentation(gameList: List<Game>): List<GamePresentation> {
        return gameList.map { game ->
            GamePresentation(
                id = game.id,
                homeTeam = game.homeTeam,
                visitantTeam = game.visitorTeam,
                homePoints = game.homePoints,
                visitantPoints = game.visitorPoints,
                gameClock = game.currentClock,
                quarter = game.quarter,
                status = game.gameStatus,
                startTime = game.startDate?.formatDateHour()
            )
        }
    }
}
