package br.com.nbagames.core

import android.app.Application
import com.google.android.material.color.DynamicColors

class NbaGamesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}
