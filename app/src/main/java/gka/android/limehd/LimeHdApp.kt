package gka.android.limehd

import android.app.Application
import android.content.Context
import gka.android.limehd.di.AppComponent
import gka.android.limehd.di.DaggerAppComponent

class LimeHdApp : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().context(this).build()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is LimeHdApp -> appComponent
        else -> applicationContext.appComponent
    }