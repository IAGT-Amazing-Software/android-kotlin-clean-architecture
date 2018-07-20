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

    companion object {
        lateinit var appContext: Context
        lateinit var applicationComponent: ApplicationComponent
    }

    init {
        appContext = this
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .repositoryModule(RepositoryModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics())
        applicationComponent.inject(this)
    }

}
