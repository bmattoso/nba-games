package br.com.nbagames.usecase.team

import br.com.nbagames.model.Team
import br.com.nbagames.repository.team.TeamRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class LoadFranchiseTeamsImpl(
    private val teamRepository: TeamRepository
) : LoadFranchiseTeams {

    override suspend fun invoke(): Flow<List<Team>> {
        val allNbaTeams = teamRepository.getTeams()
        return flow {
            if (allNbaTeams.isEmpty()) {
                throw EmptyTeamsException()
            }

            val franchiseTeams = allNbaTeams.filter { team -> team.isFranchise == true }
            emit(franchiseTeams)
        }.catch { cause: Throwable -> throw cause }
    }
}
