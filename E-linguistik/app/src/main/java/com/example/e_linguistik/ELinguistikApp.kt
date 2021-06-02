package com.example.e_linguistik

import android.app.Application
import com.example.e_linguistik.DI.databaseApp
import com.example.e_linguistik.DI.kbbiClient
import com.example.e_linguistik.DI.translatorClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ELinguistikApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@ELinguistikApp)
            modules(listOf(translatorClient, kbbiClient, databaseApp
            ))
        }
    }
}