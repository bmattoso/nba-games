package br.com.nbagames.remote.official

import br.com.nbagames.model.Official

interface OfficialRemote {
    suspend fun getOfficial(officialId: String): Official?
}
