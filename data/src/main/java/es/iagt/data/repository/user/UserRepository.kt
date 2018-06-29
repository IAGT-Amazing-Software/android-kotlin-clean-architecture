package es.iagt.data.repository.user

import es.iagt.data.model.UserModel
import io.reactivex.Observable

@Suppress("LongParameterList")
interface UserRepository {

    fun login(email: String, password: String): Observable<UserModel>
    fun register(name: String, email: String, password: String, code: String): Observable<UserModel>
}
