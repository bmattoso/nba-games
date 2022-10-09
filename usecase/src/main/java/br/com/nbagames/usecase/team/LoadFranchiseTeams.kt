package br.com.nbagames.usecase.team

import br.com.nbagames.model.Team

interface LoadFranchiseTeams {
    suspend operator fun invoke(): List<Team>
}
