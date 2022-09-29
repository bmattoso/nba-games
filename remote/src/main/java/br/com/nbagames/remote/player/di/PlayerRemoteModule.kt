package br.com.nbagames.remote.player.di

import br.com.nbagames.remote.player.mapper.PlayerMapper
import org.koin.dsl.module

val playerRemoteModule = module {
    factory { PlayerMapper() }
}
