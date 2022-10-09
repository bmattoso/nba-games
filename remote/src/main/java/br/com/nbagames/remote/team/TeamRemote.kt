package br.com.nbagames.remote.team

import br.com.nbagames.model.Team

interface TeamRemote {
    suspend fun getTeams(): List<Team>
}
