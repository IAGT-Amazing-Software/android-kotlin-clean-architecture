package es.iagt.android.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import es.iagt.android.BuildConfig
import es.iagt.android.app.AndroidApplication
import es.iagt.android.app.UIThread
import es.iagt.android.executor.JobExecutor
import es.iagt.android.utils.PreferencesUtils
import es.iagt.android.utils.SharedPreferencesManager
import es.iagt.domain.executor.PostExecutionThread
import es.iagt.domain.executor.ThreadExecutor
import javax.inject.Singleton


@Module
class ApplicationModule(val app: AndroidApplication) {

    lateinit var sharedPreferencesManager: SharedPreferencesManager

    @Provides
    @Singleton
    fun provideApplication(): AndroidApplication = app

    @Provides
    @Singleton
    fun providesApplicationContext(): Context = app

    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor = jobExecutor

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread = uiThread

    @Provides
    @Singleton
    fun provideSharedPreferencesManager(): SharedPreferencesManager {
        val sharedPreferences = app.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
        val preferencesUtils = PreferencesUtils(sharedPreferences)
        sharedPreferencesManager = SharedPreferencesManager(preferencesUtils)
        return sharedPreferencesManager
    }

}
