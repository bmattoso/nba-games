package br.com.nbagames.remote.common.extension

import br.com.nbagames.model.Quarter

fun Int.toQuarter(): Quarter = when (this) {
    2 -> Quarter.Second
    3 -> Quarter.Third
    4 -> Quarter.Fourth
    else -> Quarter.First
}
