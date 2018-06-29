package es.iagt.android.di.components

import android.content.Context
import dagger.Component
import es.iagt.android.app.AndroidApplication
import es.iagt.android.di.modules.ApplicationModule
import es.iagt.android.di.modules.RepositoryModule
import es.iagt.android.utils.Navigator
import es.iagt.android.utils.SharedPreferencesManager
import es.iagt.android.view.base.BaseActivity
import es.iagt.android.view.base.BaseFragment
import es.iagt.android.view.login.LoginActivity
import es.iagt.android.view.main.activities.MainActivity
import javax.inject.Singleton

@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = [(ApplicationModule::class), (RepositoryModule::class)])
interface ApplicationComponent {

    //inject application
    fun inject(application: AndroidApplication)

    //inject activities
    fun inject(mainActivity: MainActivity)
    fun inject(baseActivity: BaseActivity)
    fun inject(loginActivity: LoginActivity)

    //inject fragments
    fun inject(baseFragment: BaseFragment)

    //provide
    val androidApplication: AndroidApplication
    fun context(): Context
    fun navigator(): Navigator
    fun sharedPreferencesManager(): SharedPreferencesManager
}
