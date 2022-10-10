package br.com.nbagames.team.di

import br.com.nbagames.team.list.presentation.TeamListViewModel
import br.com.nbagames.usecase.team.di.teamUseCaseModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val teamPresentationModule: List<Module> = mutableListOf(
    module {

        viewModel { TeamListViewModel(loadFranchiseTeams = get()) }
    }
).apply {
    addAll(teamUseCaseModule)
}
