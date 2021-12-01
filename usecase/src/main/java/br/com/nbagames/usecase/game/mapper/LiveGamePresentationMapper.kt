package br.com.nbagames.usecase.game.mapper

import br.com.nbagames.model.LiveGame
import br.com.nbagames.usecase.game.presentation.LiveGamePresentation

class LiveGamePresentationMapper {

    fun mapLiveGamePresentation(liveGame: LiveGame): LiveGamePresentation {
        return LiveGamePresentation(
            id = liveGame.id,
            homeTeam = liveGame.homeTeam,
            awayTeam = liveGame.awayTeam,
            homePoints = liveGame.homePoints,
            awayPoints = liveGame.awayPoints,
            currentTime = liveGame.currentTime
        )
    }
}
