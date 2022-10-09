package br.com.nbagames.usecase.team

import br.com.nbagames.model.Team
import br.com.nbagames.repository.team.TeamRepository

class LoadFranchiseTeamsImpl(
    private val teamRepository: TeamRepository
) : LoadFranchiseTeams {

    override suspend fun invoke(): List<Team> {
        val allNbaTeams = teamRepository.getTeams()
        return allNbaTeams.filter { team -> team.isFranchise == true }
    }
}
