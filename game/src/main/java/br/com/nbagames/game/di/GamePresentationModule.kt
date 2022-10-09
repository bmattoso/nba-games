package br.com.nbagames.game.di

import br.com.nbagames.game.mapper.LiveGamePresentationMapper
import br.com.nbagames.game.presentation.LiveGameViewModel
import br.com.nbagames.game.presentation.detail.GameDetailViewModel
import br.com.nbagames.usecase.game.di.liveGameUseCaseModule
import br.com.nbagames.usecase.team.di.teamUseCaseModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val gamePresentationModule: List<Module> = mutableListOf(
    module {
        viewModel {
            LiveGameViewModel(
                loadLiveGames = get(),
                liveGamePresentationMapper = get()
            )
        }

        viewModel {
            GameDetailViewModel(loadGameDetail = get())
        }

        factory { LiveGamePresentationMapper() }
    }
).apply {
    addAll(liveGameUseCaseModule)
}
