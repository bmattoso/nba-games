package br.com.nbagames.repository.team.di

import br.com.nbagames.remote.team.di.teamRemoteModule
import br.com.nbagames.repository.team.TeamRepository
import br.com.nbagames.repository.team.TeamRepositoryImpl
import org.koin.dsl.module

val teamRepositoryModule = listOf(
    teamRemoteModule,
    module {
        factory<TeamRepository> { TeamRepositoryImpl(teamRemote = get()) }
    }
)
