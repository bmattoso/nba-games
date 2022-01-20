package br.com.nbagames.game.view.extension

import androidx.annotation.StringRes
import br.com.nbagames.game.R
import br.com.nbagames.model.Quarter

@StringRes
fun Quarter.getResourceTextId(): Int = when (this) {
    Quarter.First -> R.string.first_quarter
    Quarter.Second -> R.string.second_quarter
    Quarter.Third -> R.string.third_quarter
    Quarter.Fourth -> R.string.fourth_quarter
}
