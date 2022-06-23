package br.com.nbagames.designsystem.extension

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val REQUEST_DATE_PATTERN = "Y-m-d"

@SuppressLint("WeekBasedYear")
fun Date.formatToRequest(): String {
    val simpleDateFormat = SimpleDateFormat(REQUEST_DATE_PATTERN, Locale.US)
    return simpleDateFormat.format(this)
}
