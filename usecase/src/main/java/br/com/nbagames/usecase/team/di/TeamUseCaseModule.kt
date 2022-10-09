package br.com.nbagames.usecase.team.di

import br.com.nbagames.repository.team.di.teamRepositoryModule
import br.com.nbagames.usecase.team.LoadFranchiseTeams
import br.com.nbagames.usecase.team.LoadFranchiseTeamsImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val teamUseCaseModule: List<Module> = mutableListOf(
    module {
        factory<LoadFranchiseTeams> { LoadFranchiseTeamsImpl(teamRepository = get()) }
    }
).apply {
    addAll(teamRepositoryModule)
}
