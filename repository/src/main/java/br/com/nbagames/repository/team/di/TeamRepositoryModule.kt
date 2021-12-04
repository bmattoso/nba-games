package br.com.nbagames.repository.team.di

import br.com.nbagames.remote.team.di.teamRemoteModule
import org.koin.dsl.module

val teamRepositoryModule = listOf(
    teamRemoteModule,
    module {}
)
