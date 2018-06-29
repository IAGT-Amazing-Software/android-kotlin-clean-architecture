package es.iagt.android.di.components

import dagger.Component
import es.iagt.android.di.modules.RepositoryModule
import es.iagt.data.cache.AppDatabase
import es.iagt.data.repository.user.UserRepository
import javax.inject.Singleton

@Singleton
@Component(modules = [(RepositoryModule::class)])
interface RepositoryComponent {

    fun userRepository(): UserRepository
    fun appDatabase(): AppDatabase
}
