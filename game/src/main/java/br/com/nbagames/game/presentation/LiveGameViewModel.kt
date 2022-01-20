package br.com.nbagames.game.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.nbagames.game.mapper.LiveGamePresentationMapper
import br.com.nbagames.usecase.game.LoadLiveGames
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class LiveGameViewModel(
    private val loadLiveGames: LoadLiveGames,
    private val liveGamePresentationMapper: LiveGamePresentationMapper,
) : ViewModel() {

    private val mutableLiveGamesViewState = MutableStateFlow(LiveGameListUiState())
    val uiState: StateFlow<LiveGameListUiState> = mutableLiveGamesViewState

    init {
        loadLiveGameList()
    }

    fun loadLiveGameList() {
        mutableLiveGamesViewState.value = mutableLiveGamesViewState.value.copy(showLoading = true)
        viewModelScope.launch {
            loadLiveGames()
                .map { liveGameList -> liveGamePresentationMapper.mapLiveGamePresentation(liveGameList) }
                .onEach { liveGameList ->
                    val currentState = uiState.value
                    val newState = currentState.copy(
                        showLoading = false,
                        showEmptyState = liveGameList.isEmpty(),
                        liveGameList = liveGameList,
                        showError = false,
                        liveGameListError = null
                    )
                    mutableLiveGamesViewState.value = newState
                }
                .catch { throwable ->
                    Log.e("Exception", Log.getStackTraceString(throwable))
                    val currentState = uiState.value
                    val newState = currentState.copy(
                        showLoading = false,
                        showEmptyState = false,
                        liveGameList = emptyList(),
                        showError = true,
                        liveGameListError = getLiveGameListErrorFromThrowable(throwable)
                    )
                    mutableLiveGamesViewState.value = newState
                }.collect()
        }
    }

    private fun getLiveGameListErrorFromThrowable(throwable: Throwable): LiveGameListError {
        return when (throwable) {
            is RuntimeException -> LiveGameListError.Server
            else -> LiveGameListError.Unknown
        }
    }
}
