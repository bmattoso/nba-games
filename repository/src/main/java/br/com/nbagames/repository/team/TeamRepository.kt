package br.com.nbagames.repository.team

import br.com.nbagames.model.Team

interface TeamRepository {
    suspend fun getTeams(): List<Team>
}
