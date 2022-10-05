package br.com.nbagames.repository.official.di

import br.com.nbagames.remote.official.di.officialRemoteModule
import br.com.nbagames.repository.official.OfficialRepository
import br.com.nbagames.repository.official.OfficialRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val officialRepositoryModule: List<Module> = mutableListOf(
    officialRemoteModule,
    module {
        factory<OfficialRepository> {
            OfficialRepositoryImpl(officialRemote = get())
        }
    }
)
