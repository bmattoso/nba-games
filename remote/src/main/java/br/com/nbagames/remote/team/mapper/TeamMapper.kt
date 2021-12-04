package br.com.nbagames.remote.team.mapper

import br.com.nbagames.model.Team
import br.com.nbagames.remote.team.response.TeamResponse

class TeamMapper {

    fun mapTeamResponseToTeam(teamResponse: TeamResponse): Team {
        return Team(
            id = teamResponse.id,
            fullName = teamResponse.fullName,
            shortName = teamResponse.shortName,
            logo = teamResponse.logo
        )
    }
}
