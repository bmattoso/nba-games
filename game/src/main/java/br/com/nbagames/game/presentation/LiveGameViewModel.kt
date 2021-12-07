package br.com.nbagames.game.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import br.com.nbagames.usecase.game.LoadLiveGames
import br.com.nbagames.usecase.game.mapper.LiveGamePresentationMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class LiveGameViewModel(
    private val loadLiveGames: LoadLiveGames,
    private val liveGamePresentationMapper: LiveGamePresentationMapper,
) : ViewModel() {

    fun getLiveGameList(): Flow<LiveGameViewState> = flow {
        emit(LiveGameViewState.Loading)
        loadLiveGames()
            .map { liveGameList -> liveGamePresentationMapper.mapLiveGamePresentation(liveGameList) }
            .onEach { liveGameList ->
                val liveGameViewState = if (liveGameList.isEmpty()) {
                    LiveGameViewState.Empty
                } else {
                    LiveGameViewState.Loaded(liveGameList = liveGameList)
                }
                emit(liveGameViewState)
            }
            .catch { throwable ->
                Log.e("Exception", Log.getStackTraceString(throwable))
                emit(LiveGameViewState.Error(throwable = throwable))
            }.collect()
    }
}
