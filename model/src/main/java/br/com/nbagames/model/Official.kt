package br.com.nbagames.model

import android.net.Uri

private const val STORAGE_HOST_PATH =
    "https://firebasestorage.googleapis.com/v0/b/nba-games-c66b5.appspot.com/o/officials%2F"

enum class Official(
    val id: String,
    private val storageToken: String
) {
    ScottFoster("Scott Foster", "dc8c74a6-bf0e-4ba2-88a7-aab9e18eca04");

    fun getStoragePath(): String {
        val officialEncoded = Uri.encode(id)
        return "$STORAGE_HOST_PATH$officialEncoded.jpeg?alt=media&token=$storageToken"
    }
}

fun String.toOfficial(): Official? {
    Official.values().forEach { official: Official ->
        if (official.id == this) {
            return official
        }
    }
    return null
}
