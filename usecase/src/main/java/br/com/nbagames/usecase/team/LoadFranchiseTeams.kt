package br.com.nbagames.usecase.team

import br.com.nbagames.model.Team
import kotlinx.coroutines.flow.Flow

interface LoadFranchiseTeams {
    suspend operator fun invoke(): Flow<List<Team>>
}
