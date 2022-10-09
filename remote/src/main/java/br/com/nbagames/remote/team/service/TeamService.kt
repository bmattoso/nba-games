package br.com.nbagames.remote.team.service

import br.com.nbagames.remote.team.response.TeamResponse
import retrofit2.http.GET

interface TeamService {

    @GET("teams/")
    fun getAllNbaTeams(): List<TeamResponse>
}
