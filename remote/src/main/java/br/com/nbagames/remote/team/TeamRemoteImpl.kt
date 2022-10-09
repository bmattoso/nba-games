package br.com.nbagames.remote.team

import br.com.nbagames.model.Team
import br.com.nbagames.remote.team.mapper.TeamMapper
import br.com.nbagames.remote.team.service.TeamService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class TeamRemoteImpl(
    private val teamService: TeamService,
    private val teamMapper: TeamMapper
) : TeamRemote {

    override suspend fun getTeams(): List<Team> {
        val allTeamsResponse = withContext(IO) { teamService.getAllNbaTeams() }
        return teamMapper.mapTeamResponseList(allTeamsResponse)
    }
}
