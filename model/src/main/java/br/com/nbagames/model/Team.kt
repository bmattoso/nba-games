package br.com.nbagames.model

import android.graphics.Color

data class Team(
    val id: Int,
    val name: String,
    val nickname: String,
    val logo: String?,
    val isFavorite: Boolean = false,
    val isFranchise: Boolean? = null,
    val color: Int = Color.parseColor("#FFFFFF")
)
