package br.com.nbagames.remote.game.di

import br.com.nbagames.remote.game.GameRemote
import br.com.nbagames.remote.game.GameRemoteImpl
import br.com.nbagames.remote.game.mapper.GameMapper
import br.com.nbagames.remote.game.service.GameService
import org.koin.dsl.module
import retrofit2.Retrofit

val gameRemoteModule = module {

    factory { GameMapper(teamMapper = get()) }

    factory<GameRemote> {
        GameRemoteImpl(
            gameService = get(),
            gameMapper = get()
        )
    }

    factory {
        val retrofit = get<Retrofit>()
        retrofit.create(GameService::class.java)
    }
}
