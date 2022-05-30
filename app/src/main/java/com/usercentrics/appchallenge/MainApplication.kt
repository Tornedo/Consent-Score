package com.usercentrics.appchallenge

import android.app.Application
import com.usercentrics.sdk.Usercentrics
import com.usercentrics.sdk.UsercentricsOptions
import com.usercentrics.sdk.models.common.UsercentricsLoggerLevel
import com.usercentrics.appchallenge.BuildConfig
import com.usercentrics.appchallenge.di.ApplicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Initialize Usercentrics
        val settingsId = BuildConfig.SEETING_ID
        val options = UsercentricsOptions(settingsId, loggerLevel = UsercentricsLoggerLevel.DEBUG)
        Usercentrics.initialize(this, options)

        // Start Koin
        startKoin{
            androidLogger()  // use AndroidLogger as Koin Logger - default Level.INFO
            androidContext(this@MainApplication) // use the Android context given there
            androidFileProperties() // load properties from assets/koin.properties file
            koin.loadModules(// module list
                listOf(
                    ApplicationModule.applicationModule,
                )
            )
            //koin.createRootScope()
        }
    }
    
}
