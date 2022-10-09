package br.com.nbagames.repository.team

import br.com.nbagames.model.Team
import br.com.nbagames.remote.team.TeamRemote

class TeamRepositoryImpl(private val teamRemote: TeamRemote) : TeamRepository {

    override suspend fun getTeams(): List<Team> {
        val teams = teamRemote.getTeams()

        return teams.map { team ->
            val teamColor = teamRemote.getTeamColor(team.id)
            return@map if (!teamColor.isNullOrBlank()) team.copy(color = teamColor) else team
        }
    }
}
