package br.com.nbagames.remote.team.service

import br.com.nbagames.remote.team.response.TeamListResponse
import retrofit2.http.GET

interface TeamService {

    @GET("teams/")
    suspend fun getAllNbaTeams(): TeamListResponse
}
