package br.com.nbagames.remote.game.di

import br.com.nbagames.remote.game.GameRemote
import br.com.nbagames.remote.game.GameRemoteImpl
import br.com.nbagames.remote.game.mapper.GameMapper
import org.koin.dsl.module

val gameRemoteModule = module {

    factory { GameMapper() }

    factory<GameRemote> {
        GameRemoteImpl(
            gameService = get(),
            gameMapper = get()
        )
    }
}
