package br.com.nbagames.repository.game.di

import br.com.nbagames.repository.game.GameRepository
import br.com.nbagames.repository.game.GameRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val gameRepositoryModule: List<Module> = mutableListOf(
    module {
        factory<GameRepository> { GameRepositoryImpl() }
    }
).apply { }
