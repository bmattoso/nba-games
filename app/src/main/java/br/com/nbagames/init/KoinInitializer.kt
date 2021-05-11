package br.com.nbagames.init

import android.content.Context
import androidx.startup.Initializer
import br.com.nbagames.di.networkModule
import br.com.nbagames.game.di.liveGameModule
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

@ExperimentalSerializationApi
class KoinInitializer : Initializer<KoinApplication> {

    override fun create(context: Context): KoinApplication = startKoin {
        androidContext(context)
        modules(
            listOf(
                networkModule,
                liveGameModule
            )
        )
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}