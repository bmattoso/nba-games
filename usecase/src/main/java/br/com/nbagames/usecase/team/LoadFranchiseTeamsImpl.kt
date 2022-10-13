package br.com.nbagames.usecase.team

import br.com.nbagames.model.Team
import br.com.nbagames.repository.team.TeamRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

class LoadFranchiseTeamsImpl(
    private val teamRepository: TeamRepository
) : LoadFranchiseTeams {

    override suspend fun invoke(): Flow<List<Team>> {
        return teamRepository.getTeams().map { allTeams ->
            if (allTeams.isEmpty()) {
                throw EmptyTeamsException()
            }

            allTeams.filter { team -> team.isFranchise == true }
        }.catch { cause: Throwable -> throw cause }
    }
}
