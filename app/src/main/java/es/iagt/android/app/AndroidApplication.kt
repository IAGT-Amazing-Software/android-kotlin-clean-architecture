package es.iagt.android.app

import android.content.Context
import android.graphics.Typeface
import android.support.multidex.MultiDexApplication
import com.crashlytics.android.Crashlytics
import es.iagt.android.di.components.ApplicationComponent
import es.iagt.android.di.components.DaggerApplicationComponent
import es.iagt.android.di.modules.ApplicationModule
import es.iagt.android.di.modules.RepositoryModule
import io.fabric.sdk.android.Fabric


class AndroidApplication : MultiDexApplication() {

    init {
        myInstance = this
    }

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics())
        initializeInjector()
        applicationComponent.inject(this)
    }

    private fun initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .repositoryModule(RepositoryModule(this))
                .build()
    }

    companion object {

        private var myInstance: AndroidApplication? = null

        fun getApplicationContext(): Context {
            return myInstance!!.applicationContext
        }

    }
}
