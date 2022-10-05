package br.com.nbagames.usecase.game.di

import br.com.nbagames.repository.game.di.gameRepositoryModule
import br.com.nbagames.repository.official.di.officialRepositoryModule
import br.com.nbagames.usecase.game.LoadGameDetail
import br.com.nbagames.usecase.game.LoadGameDetailImpl
import br.com.nbagames.usecase.game.LoadLiveGames
import br.com.nbagames.usecase.game.LoadLiveGamesImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val liveGameUseCaseModule: List<Module> = mutableListOf(
    module {
        factory<LoadLiveGames> { LoadLiveGamesImpl(gameRepository = get()) }

        factory<LoadGameDetail> { LoadGameDetailImpl(gameRepository = get(), officialRepository = get()) }
    }
).apply {
    addAll(gameRepositoryModule)
    addAll(officialRepositoryModule)
}
