package es.iagt.android.di.modules

import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import es.iagt.android.app.AndroidApplication
import es.iagt.data.cache.AppDatabase
import es.iagt.data.repository.user.UserDataRepository
import es.iagt.data.repository.user.UserRepository
import es.iagt.data.util.ConnectionUtils
import javax.inject.Singleton

@Module
internal class RepositoryModule(val app: AndroidApplication) {

    @Provides
    @Singleton
    fun provideUserRepository(userRepository: UserDataRepository): UserRepository {
        return userRepository
    }


    @Provides
    @Singleton
    fun provideAppDatabase(): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "database")
                // allow queries on the main thread.
                // Don't do this on a real app! See PersistenceBasicSample for an example.
//                .allowMainThreadQueries()
                .build()
    }

    @Provides
    @Singleton
    fun provideConnectionUtils(): ConnectionUtils {
        return ConnectionUtils(app)
    }


}


