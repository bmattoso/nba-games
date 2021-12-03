package br.com.nbagames.game.di

import br.com.nbagames.game.presentation.LiveGameViewModel
import br.com.nbagames.usecase.game.module.liveGameUseCaseModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val liveGamePresentationModule: List<Module> = mutableListOf(
    module {
        viewModel { LiveGameViewModel() }
    }
).apply { addAll(liveGameUseCaseModule) }