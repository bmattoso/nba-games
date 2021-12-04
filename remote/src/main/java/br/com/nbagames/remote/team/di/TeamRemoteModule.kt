package br.com.nbagames.remote.team.di

import br.com.nbagames.remote.team.mapper.TeamMapper
import org.koin.dsl.module

val teamRemoteModule = module {
    factory { TeamMapper() }
}
