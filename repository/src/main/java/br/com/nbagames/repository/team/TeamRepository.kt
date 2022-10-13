package br.com.nbagames.repository.team

import br.com.nbagames.model.Team
import kotlinx.coroutines.flow.Flow

interface TeamRepository {
    suspend fun getTeams(): Flow<List<Team>>
    suspend fun getTeamColor(teamId: Int): Int?
}
