package es.iagt.data.repository.user

import es.iagt.data.entity.UserEntity
import es.iagt.data.net.user.request.LoginRequestDTO
import es.iagt.data.net.user.request.RegisterRequestDTO
import io.reactivex.Observable


interface UserDataStore {

    fun login(loginRequestDTO: LoginRequestDTO): Observable<UserEntity>?
    fun register(registerRequestDTO: RegisterRequestDTO): Observable<UserEntity>?
}
