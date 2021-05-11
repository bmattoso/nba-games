package br.com.nbagames.game.presentation

sealed class LiveGameListState {
    object Loading : LiveGameListState()
    object Empty : LiveGameListState()
    class Loaded(val liveGameList: List<Any>) : LiveGameListState()
    class Error(val throwable: Throwable) : LiveGameListState()
}
