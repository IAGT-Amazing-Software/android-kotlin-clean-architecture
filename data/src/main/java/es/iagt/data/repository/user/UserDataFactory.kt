package es.iagt.data.repository.user

import es.iagt.data.cache.DBManager
import es.iagt.data.net.RestApi
import es.iagt.data.repository.user.source.UserCloudDataSource
import es.iagt.data.repository.user.source.UserDBDataSource
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserDataFactory @Inject constructor(private val restApi: RestApi, private val dbManager: DBManager) {

    fun createCloudDataStore(): UserDataStore {
        return UserCloudDataSource(restApi, dbManager)
    }

    fun createDBDataStore(): UserDataStore {
        return UserDBDataSource(dbManager)
    }

}
