package br.com.nbagames.usecase.game.mapper

import br.com.nbagames.model.Game
import br.com.nbagames.usecase.game.presentation.LiveGamePresentation

class LiveGamePresentationMapper {

    fun mapLiveGamePresentation(gameList: List<Game>): List<LiveGamePresentation> {
        return gameList.map { liveGame ->
            LiveGamePresentation(
                id = liveGame.id,
                homeTeam = liveGame.homeTeam,
                awayTeam = liveGame.visitantTeam,
                homePoints = liveGame.homePoints,
                awayPoints = liveGame.visitantPoints,
                currentTime = liveGame.currentTime
            )
        }
    }
}
