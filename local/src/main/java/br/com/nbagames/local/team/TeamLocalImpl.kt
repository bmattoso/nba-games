package br.com.nbagames.local.team

import br.com.nbagames.local.dao.TeamDao
import br.com.nbagames.model.Team
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TeamLocalImpl(
    private val teamDao: TeamDao,
    private val teamEntityMapper: TeamEntityMapper
) : TeamLocal {

    override suspend fun getTeamById(teamId: Int): Team? = try {
        val teamEntity = teamDao.getTeamById(teamId)
        teamEntity?.let { teamEntityMapper.mapEntityToModel(teamEntity) }
    } catch (ex: Exception) {
        null
    }

    override suspend fun getTeamByIdStream(teamId: Int): Flow<Team?> = teamDao.getTeamByIdStream(teamId)
        .map { teamEntity -> teamEntity?.let { teamEntityMapper.mapEntityToModel(teamEntity) } }

    override suspend fun toggleFavoriteTeam(teamId: Int, isFavorite: Boolean) {}

    override suspend fun saveTeam(team: Team) {
        teamDao.insertTeam(teamEntityMapper.mapModelToEntity(team))
    }

    override suspend fun getAllTeamsStream(): Flow<List<Team>> {
        return teamDao.getAllTeams().map { teamList ->
            teamList.map { teamEntity -> teamEntityMapper.mapEntityToModel(teamEntity) }
        }
    }

    override fun saveAllTeams(teams: List<Team>) {
        val teamsEntity = teams.map { team -> teamEntityMapper.mapModelToEntity(team) }
        teamDao.insertTeams(teamsEntity)
    }
}
