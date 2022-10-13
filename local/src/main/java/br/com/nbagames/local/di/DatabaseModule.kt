package br.com.nbagames.local.di

import android.content.Context
import androidx.room.Room
import br.com.nbagames.local.database.AppDatabase
import br.com.nbagames.local.team.TeamEntityMapper
import br.com.nbagames.local.team.TeamLocal
import br.com.nbagames.local.team.TeamLocalImpl
import org.koin.dsl.module

val databaseModule = module {

    single {
        val applicationContext = get<Context>()

        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "nba-games-database"
        ).build()
    }

    single {
        val database = get<AppDatabase>()
        database.teamDao()
    }

    single { TeamEntityMapper() }

    factory<TeamLocal> { TeamLocalImpl(teamDao = get(), teamEntityMapper = get()) }
}
