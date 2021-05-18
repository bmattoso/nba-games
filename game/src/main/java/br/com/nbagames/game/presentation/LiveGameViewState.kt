package br.com.nbagames.game.presentation

import br.com.nbagames.model.LiveGame

sealed class LiveGameViewState {
    object Loading : LiveGameViewState()
    object Empty : LiveGameViewState()
    class Loaded(val liveGameList: List<LiveGame>) : LiveGameViewState()
    class Error(val throwable: Throwable) : LiveGameViewState()
}
