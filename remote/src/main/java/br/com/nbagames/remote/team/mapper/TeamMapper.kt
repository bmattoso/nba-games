package br.com.nbagames.remote.team.mapper

import br.com.nbagames.model.Team
import br.com.nbagames.remote.team.response.TeamResponse

class TeamMapper {

    fun mapTeamResponseToTeam(teamResponse: TeamResponse) = Team(
        id = teamResponse.id,
        name = teamResponse.name,
        nickname = teamResponse.code,
        logo = teamResponse.logo
    )

    fun mapTeamResponseList(teamsResponse: List<TeamResponse>): List<Team> {
        return teamsResponse.map { teamResponse -> mapTeamResponseToTeam(teamResponse) }
    }
}
