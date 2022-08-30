package br.com.nbagames.init

import android.content.Context
import androidx.startup.Initializer
import br.com.nbagames.BuildConfig
import br.com.nbagames.game.di.gamePresentationModule
import br.com.nbagames.remote.di.NetworkModule.getNetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

class KoinInitializer : Initializer<KoinApplication> {

    override fun create(context: Context): KoinApplication = startKoin {
        val networkModule = getNetworkModule(
            baseUrl = BuildConfig.BACKEND_BASE_URL,
            apiHost = BuildConfig.X_RAPID_API_HOST,
            apiKey = BuildConfig.X_RAPID_API_KEY
        )
        val allModules = mutableListOf(networkModule).apply {
            addAll(gamePresentationModule)
        }

        androidContext(context)
        modules(allModules)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}
