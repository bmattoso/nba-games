package br.com.nbagames.model

import androidx.annotation.StringRes

enum class GameStatus(
    val code: Int,
    @StringRes val descriptionResId: Int
) {
    SCHEDULED(1, R.string.scheduled),
    RUNNING(2, R.string.in_game),
    FINISHED(3, R.string.finished),
    HALF_TIME(4, R.string.half_time);
}
