package br.com.nbagames.game.presentation

import br.com.nbagames.usecase.game.presentation.LiveGamePresentation

sealed class LiveGameViewState {
    object Loading : LiveGameViewState()
    object Empty : LiveGameViewState()
    class Loaded(val liveGameList: List<LiveGamePresentation>) : LiveGameViewState()
    class Error(val throwable: Throwable) : LiveGameViewState()
}
