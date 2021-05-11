package br.com.nbagames.game.di

import br.com.nbagames.game.presentation.LiveGameViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val liveGameModule = module {
    viewModel { LiveGameViewModel() }
}
