package es.iagt.data.repository.user

import es.iagt.data.entity.mapper.UserMapper
import es.iagt.data.model.UserModel
import es.iagt.data.util.ConnectionUtils
import io.reactivex.Observable
import javax.inject.Inject


class UserDataRepository
@Inject
constructor(private val mapper: UserMapper,
            private val dataFactory: UserDataFactory,
            private val connectionUtils: ConnectionUtils) : UserRepository {

    override fun login(email: String, password: String): Observable<UserModel> {
        return dataFactory.createCloudDataStore()
                .login(mapper.transform(email, password))!!
                .map<UserModel>({mapper.transform(it)})
    }

    override fun register(name: String, email: String, password: String, code: String): Observable<UserModel> {
        return dataFactory.createCloudDataStore()
                .register(mapper.transformRegister(name, email, password, code))!!
                .map<UserModel>({mapper.transform(it)})
    }
}
