package br.com.nbagames.model

enum class Quarter {
    First, Second, Third, Fourth
}

fun String.toQuarter(): Quarter = when (this) {
    "2/4" -> Quarter.Second
    "3/4" -> Quarter.Third
    "4/4" -> Quarter.Fourth
    else -> Quarter.First
}
