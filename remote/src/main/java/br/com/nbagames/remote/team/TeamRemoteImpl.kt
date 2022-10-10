package br.com.nbagames.remote.team

import android.graphics.Color
import br.com.nbagames.model.Team
import br.com.nbagames.remote.firebase.FirebaseRealTimeDatabase
import br.com.nbagames.remote.team.mapper.TeamMapper
import br.com.nbagames.remote.team.response.TeamColor
import br.com.nbagames.remote.team.service.TeamService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

private const val TEAMS_NODE = "teams"

class TeamRemoteImpl(
    private val firebaseRealTimeDatabase: FirebaseRealTimeDatabase,
    private val teamService: TeamService,
    private val teamMapper: TeamMapper
) : TeamRemote {

    override suspend fun getTeams(): List<Team> {
        val allTeamsResponse = withContext(IO) { teamService.getAllNbaTeams() }
        return teamMapper.mapTeamResponseList(allTeamsResponse.teams)
    }

    override suspend fun getTeamColor(teamId: Int): Int? {
        val teamColorString = firebaseRealTimeDatabase.getJsonNode<TeamColor>(TEAMS_NODE, teamId.toString())?.color
        return if (teamColorString != null) Color.parseColor(teamColorString) else null
    }
}
