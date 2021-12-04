package br.com.nbagames.usecase.team.di

import br.com.nbagames.repository.team.di.teamRepositoryModule
import org.koin.core.module.Module
import org.koin.dsl.module

val teamUseCaseModule: List<Module> = mutableListOf(
    module { }
).apply {
    addAll(teamRepositoryModule)
}
