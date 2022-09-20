package br.com.nbagames.model

import androidx.annotation.StringRes

enum class Quarter(
    val code: Int,
    val shortDescription: String,
    @StringRes val descriptionResId: Int
) {
    First(1, "Q1", R.string.first_quarter),
    Second(2, "Q2", R.string.second_quarter),
    Third(3, "Q3", R.string.third_quarter),
    Fourth(4, "Q4", R.string.fourth_quarter)
}

fun Int.toQuarter(): Quarter {
    Quarter.values().forEach { quarter ->
        if (quarter.code == this) {
            return quarter
        }
    }

    return Quarter.First
}
