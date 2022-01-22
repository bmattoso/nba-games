package br.com.nbagames.game.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.nbagames.designsystem.extension.formatNumberTwoDigits
import br.com.nbagames.game.mapper.LiveGamePresentationMapper
import br.com.nbagames.usecase.game.LoadLiveGames
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

private const val INITIAL_COUNTDOWN_TEXT = "01:00"
private const val COUNTDOWN_DELAY = 1000L
private const val INITIAL_COUNTDOWN_VALUE = 60

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
                    if (liveGameList.isNotEmpty()) {
                        startTimer()
                    }
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

    fun toggleCountdownTimer() {
        val newIsCountdownAvailable = !uiState.value.isCountdownAvailable

        if (newIsCountdownAvailable) {
            startTimer()
            mutableLiveGamesViewState.value = uiState.value.copy(isCountdownAvailable = true)
        } else {
            mutableLiveGamesViewState.value = uiState.value.copy(
                isCountdownAvailable = false,
                countdownTimer = INITIAL_COUNTDOWN_TEXT
            )
        }
    }

    private fun startTimer() {
        var countdownTimer = INITIAL_COUNTDOWN_VALUE
        viewModelScope.launch {
            do {
                val countdownSeconds = formatCountdownTimer(countdownTimer)
                mutableLiveGamesViewState.value = uiState.value.copy(countdownTimer = countdownSeconds)

                countdownTimer--
                if (countdownTimer <= 0 && uiState.value.isCountdownAvailable) {
                    loadLiveGameList()
                } else {
                    delay(COUNTDOWN_DELAY)
                }
            } while (uiState.value.isCountdownAvailable && countdownTimer > 0)
        }
    }

    private fun getLiveGameListErrorFromThrowable(throwable: Throwable): LiveGameListError {
        return when (throwable) {
            is RuntimeException -> LiveGameListError.Server
            else -> LiveGameListError.Unknown
        }
    }

    private fun formatCountdownTimer(value: Int): String = "00:${value.formatNumberTwoDigits()}"
}
