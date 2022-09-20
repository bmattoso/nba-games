package br.com.nbagames.game.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.nbagames.game.mapper.LiveGamePresentationMapper
import br.com.nbagames.usecase.game.LoadLiveGames
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

private const val INITIAL_COUNTDOWN_TEXT = "03:00"
private const val COUNTDOWN_DELAY = 1000L
private const val INITIAL_COUNTDOWN_VALUE = 180

class LiveGameViewModel(
    private val loadLiveGames: LoadLiveGames,
    private val liveGamePresentationMapper: LiveGamePresentationMapper
) : ViewModel() {

    private val mutableLiveGamesViewState = MutableStateFlow(LiveGameListUiState())
    val uiState: StateFlow<LiveGameListUiState> = mutableLiveGamesViewState

    init {
        loadLiveGameList()
    }

    fun loadLiveGameList() {
        mutableLiveGamesViewState.value = mutableLiveGamesViewState.value.copy(showLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
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
                }.flowOn(Dispatchers.IO).collect()
        }
    }

    fun onGameClick() {
        resetTimer()
    }

    fun toggleCountdownTimer() {
        val enableCountdown = !uiState.value.isCountdownAvailable

        if (enableCountdown) {
            loadLiveGameList()
            startTimer()
            mutableLiveGamesViewState.value = uiState.value.copy(isCountdownAvailable = true)
        } else {
            resetTimer()
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

    private fun resetTimer() {
        mutableLiveGamesViewState.value = uiState.value.copy(
            isCountdownAvailable = false,
            countdownTimer = INITIAL_COUNTDOWN_TEXT
        )
    }

    private fun getLiveGameListErrorFromThrowable(throwable: Throwable): LiveGameListError {
        return when (throwable) {
            is RuntimeException -> LiveGameListError.Server
            else -> LiveGameListError.Unknown
        }
    }

    private fun formatCountdownTimer(value: Int): String = value.seconds.toString()
}
