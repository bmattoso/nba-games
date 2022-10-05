package br.com.nbagames.repository.official

import br.com.nbagames.model.Official

interface OfficialRepository {
    suspend fun getOfficialImageByName(officialName: String): Official
}
