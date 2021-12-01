package br.com.nbagames.init

import android.content.Context
import androidx.startup.Initializer
import br.com.nbagames.di.networkModule
import br.com.nbagames.game.di.liveGamePresentationModule
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

@ExperimentalSerializationApi
class KoinInitializer : Initializer<KoinApplication> {

    override fun create(context: Context): KoinApplication = startKoin {
        val allModules = mutableListOf(networkModule).apply {
            addAll(liveGamePresentationModule)
        }

        androidContext(context)
        modules(allModules)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}
