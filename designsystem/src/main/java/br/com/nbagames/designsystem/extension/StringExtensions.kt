package br.com.nbagames.designsystem.extension

import java.util.IllegalFormatException

fun String.formatGameClock(): String {
    try {
        val clockNumbers = this.split(":").map { clockNumberString -> clockNumberString.toInt() }
        if (clockNumbers.size == 2) {
            val clockPattern = "%02d : %02d"

            return clockPattern.format(clockNumbers[0], clockNumbers[1])
        }

        return this
    } catch (exception: Exception) {
        return this
    }
}

fun Int.formatNumberTwoDigits(): String {
    return try {
        "%02d".format(this)
    } catch (exception: IllegalFormatException) {
        return this.toString()
    }
}
