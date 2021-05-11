package br.com.nbagames.game.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LiveGameViewModel : ViewModel() {

    fun getLiveGameList() : Flow<LiveGameListState> = flow {
        emit(LiveGameListState.Loading)
    }
}
