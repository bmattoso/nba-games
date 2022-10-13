package br.com.nbagames.local.team

import br.com.nbagames.model.Team
import kotlinx.coroutines.flow.Flow

interface TeamLocal {
    suspend fun getTeamById(teamId: Int): Team?
    suspend fun getTeamByIdStream(teamId: Int): Flow<Team?>
    suspend fun toggleFavoriteTeam(teamId: Int, isFavorite: Boolean)
    suspend fun saveTeam(team: Team)
    suspend fun getAllTeamsStream(): Flow<List<Team>>
    fun saveAllTeams(teams: List<Team>)
}
