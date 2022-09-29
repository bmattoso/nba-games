package br.com.nbagames.remote.game.di

import br.com.nbagames.remote.game.GameRemote
import br.com.nbagames.remote.game.GameRemoteImpl
import br.com.nbagames.remote.game.mapper.GameMapper
import br.com.nbagames.remote.game.mapper.GameStatisticsMapper
import br.com.nbagames.remote.game.service.GameService
import org.koin.core.scope.get
import org.koin.dsl.module
import retrofit2.Retrofit

val gameRemoteModule = module {

    factory { GameMapper(teamMapper = get()) }

    factory { GameStatisticsMapper(playerMapper = get()) }

    factory<GameRemote> {
        GameRemoteImpl(
            gameService = get(),
            gameMapper = get(),
            gameStatisticsMapper = get()
        )
    }

    factory {
        val retrofit = get<Retrofit>()
        retrofit.create(GameService::class.java)
    }
}
