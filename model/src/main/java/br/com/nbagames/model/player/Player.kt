package br.com.nbagames.model.player

data class Player(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val position: String?
) {

    fun getCompletePlayerIdentification(): String {
        return if (position.isNullOrBlank()) {
            "${firstName[0]}. $lastName"
        } else {
            "${firstName[0]}. $lastName ($position)"
        }
    }
}
