package br.com.nbagames.remote.official

import br.com.nbagames.model.Official
import br.com.nbagames.remote.firebase.FirebaseRealTimeDatabase

private const val OFFICIALS_NODE = "officials"

class OfficialRemoteImpl(
    private val firebaseRealTimeDatabase: FirebaseRealTimeDatabase
) : OfficialRemote {

    override suspend fun getOfficial(officialId: String): Official? {
        return firebaseRealTimeDatabase.getJsonNode<Official>(nodeKey = OFFICIALS_NODE, childKey = officialId)
    }
}
