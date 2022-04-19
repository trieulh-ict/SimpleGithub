package io.trieulh.simplegithub

import android.app.Application
import io.trieulh.simplegithub.di.simpleModule
import io.trieulh.simplegithub.journey.auth.di.authFlowModule
import io.trieulh.simplegithub.journey.dashboard.di.dashboardFlowModule
import io.trieulh.simpledomain.di.domainModule
import io.trieulh.simpleservice.di.serviceModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class SimpleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@SimpleApplication)
            modules(
                serviceModule,
                domainModule,
                simpleModule,
                authFlowModule,
                dashboardFlowModule
            )
        }
    }
}