package br.com.nbagames.core.error

import androidx.annotation.RawRes
import androidx.annotation.StringRes
import br.com.nbagames.core.R

enum class CommonError(
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

fun Throwable.toCommonError(): CommonError {
    return when (this) {
        is RuntimeException -> CommonError.Server
        else -> CommonError.Unknown
    }
}
