package br.com.nbagames.remote.team.di

import br.com.nbagames.remote.team.TeamRemote
import br.com.nbagames.remote.team.TeamRemoteImpl
import br.com.nbagames.remote.team.mapper.TeamMapper
import br.com.nbagames.remote.team.service.TeamService
import org.koin.dsl.module
import retrofit2.Retrofit

val teamRemoteModule = module {

    factory {
        val retrofit = get<Retrofit>()
        retrofit.create(TeamService::class.java)
    }

    factory { TeamMapper() }

    factory<TeamRemote> { TeamRemoteImpl(teamService = get(), teamMapper = get(), firebaseRealTimeDatabase = get()) }
}
