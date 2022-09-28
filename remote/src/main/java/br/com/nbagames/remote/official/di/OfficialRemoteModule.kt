package br.com.nbagames.remote.official.di

import br.com.nbagames.remote.official.OfficialRemote
import br.com.nbagames.remote.official.OfficialRemoteImpl
import org.koin.dsl.module

val officialRemoteModule = module {
    factory<OfficialRemote> { OfficialRemoteImpl(firebaseRealTimeDatabase = get()) }
}
