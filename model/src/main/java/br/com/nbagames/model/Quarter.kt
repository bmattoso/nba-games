package br.com.nbagames.model

enum class Quarter {
    First, Second, Third, Fourth
}

fun Int.toQuarter(): Quarter = when (this) {
    2 -> Quarter.Second
    3 -> Quarter.Third
    4 -> Quarter.Fourth
    else -> Quarter.First
}
