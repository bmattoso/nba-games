package br.com.nbagames.usecase.game.module

import br.com.nbagames.repository.game.di.gameRepositoryModule
import br.com.nbagames.usecase.game.LoadLiveGames
import br.com.nbagames.usecase.game.LoadLiveGamesImpl
import br.com.nbagames.usecase.game.mapper.LiveGamePresentationMapper
import org.koin.core.module.Module
import org.koin.dsl.module

val liveGameUseCaseModule: List<Module> = mutableListOf(
    module {

        factory<LoadLiveGames> { LoadLiveGamesImpl(gameRepository = get()) }

        factory { LiveGamePresentationMapper() }
    }
).apply { addAll(gameRepositoryModule) }
