package com.mz.blackink

import android.app.Application
import com.mz.blackink.di.initializeKoin
import org.koin.android.ext.koin.androidContext

class BlackInkApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeKoin { androidContext(this@BlackInkApplication) }
    }
}
