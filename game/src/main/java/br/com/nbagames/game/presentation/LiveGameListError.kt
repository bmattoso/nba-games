package br.com.nbagames.game.presentation

import androidx.annotation.RawRes
import androidx.annotation.StringRes
import br.com.nbagames.game.R

enum class LiveGameListError(
    @StringRes val message: Int,
    @RawRes val animationRes: Int,
    @StringRes val actionMessage: Int
) {
    Server(
        message = R.string.server_communication_failed,
        animationRes = R.raw.server_error,
        actionMessage = R.string.load_again
    ),
    Unknown(
        message = R.string.unknown_error_message,
        animationRes = R.raw.questioning,
        actionMessage = R.string.load_again
    )
}
