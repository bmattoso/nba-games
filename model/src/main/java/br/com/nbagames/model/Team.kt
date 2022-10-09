package br.com.nbagames.model

data class Team(
    val id: Int,
    val name: String,
    val nickname: String,
    val logo: String,
    val isFranchise: Boolean? = null,
    val color: String = "#FFFFFF"
)
