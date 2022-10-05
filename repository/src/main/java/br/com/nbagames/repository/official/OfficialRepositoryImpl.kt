package br.com.nbagames.repository.official

import br.com.nbagames.model.Official
import br.com.nbagames.remote.official.OfficialRemote

class OfficialRepositoryImpl(private val officialRemote: OfficialRemote) : OfficialRepository {

    override suspend fun getOfficialImageByName(officialName: String): Official {
        return officialRemote.getOfficial(officialName) ?: Official(id = officialName)
    }
}
