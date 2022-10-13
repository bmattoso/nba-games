package br.com.nbagames.repository.team

import br.com.nbagames.local.team.TeamLocal
import br.com.nbagames.model.Team
import br.com.nbagames.remote.team.TeamRemote
import kotlinx.coroutines.flow.Flow

class TeamRepositoryImpl(
    private val teamRemote: TeamRemote,
    private val teamLocal: TeamLocal
) : TeamRepository {

    override suspend fun getTeams(): Flow<List<Team>> {
        return teamLocal.getAllTeamsStream().also {
            val remoteTeams = teamRemote.getTeams()
            teamLocal.saveAllTeams(remoteTeams)
        }
    }

    override suspend fun getTeamColor(teamId: Int): Int? {
        val teamColor = teamRemote.getTeamColor(teamId)
        if (teamColor != null) {
            val storedTeam = teamLocal.getTeamById(teamId)?.copy(color = teamColor)
            storedTeam?.let { teamLocal.saveTeam(storedTeam) }
        }

        return teamColor
    }
}
