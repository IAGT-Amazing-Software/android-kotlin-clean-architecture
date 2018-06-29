package es.iagt.data.repository.user.source

import es.iagt.data.cache.DBManager
import es.iagt.data.entity.UserEntity
import es.iagt.data.net.user.request.LoginRequestDTO
import es.iagt.data.net.user.request.RegisterRequestDTO
import es.iagt.data.repository.user.UserDataStore
import io.reactivex.Observable


class UserDBDataSource(private val dbManager: DBManager) : UserDataStore {

    override fun login(loginRequestDTO: LoginRequestDTO): Observable<UserEntity>? {
        return null
    }

    override fun register(registerRequestDTO: RegisterRequestDTO): Observable<UserEntity>? {
        return null
    }

}
