package br.com.nbagames.remote.team.di

import br.com.nbagames.remote.team.TeamRemote
import br.com.nbagames.remote.team.TeamRemoteImpl
import br.com.nbagames.remote.team.mapper.TeamMapper
import org.koin.dsl.module

val teamRemoteModule = module {
    factory { TeamMapper() }
    factory<TeamRemote> { TeamRemoteImpl(teamService = get(), teamMapper = get()) }
}
