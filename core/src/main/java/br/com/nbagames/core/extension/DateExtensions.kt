package br.com.nbagames.core.extension

import android.annotation.SuppressLint
import android.os.Build
import org.koin.core.context.GlobalContext.get
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.util.Date
import java.util.Locale

const val REQUEST_DATE_PATTERN = "Y-MM-dd"
const val RESPONSE_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS"
const val DATE_HOUR_PATTERN = "dd/MM - HH:mm"

@SuppressLint("WeekBasedYear")
fun Date.formatToRequest(
    locale: Locale = get().get()
): String {
    val simpleDateFormat = SimpleDateFormat(REQUEST_DATE_PATTERN, locale)
    return simpleDateFormat.format(this)
}

fun String.formatToDate(
    pattern: String = RESPONSE_DATE_PATTERN,
    locale: Locale = get().get()
): Date? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    val instantDate = Instant.parse(this)
    Date.from(instantDate.atZone(ZoneId.systemDefault()).toInstant())
} else {
    val formatter = SimpleDateFormat(pattern, locale)
    formatter.parse(this)
}

fun Date.formatDateHour(
    pattern: String = DATE_HOUR_PATTERN,
    locale: Locale = get().get()
): String {
    val formatter = SimpleDateFormat(pattern, locale)
    return formatter.format(this)
}
