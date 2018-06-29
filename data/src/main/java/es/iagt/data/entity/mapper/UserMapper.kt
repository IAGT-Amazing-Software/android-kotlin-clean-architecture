package es.iagt.data.entity.mapper

import es.iagt.data.entity.UserEntity
import es.iagt.data.model.UserModel
import es.iagt.data.net.user.request.LoginRequestDTO
import es.iagt.data.net.user.request.RegisterRequestDTO
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserMapper
@Inject
constructor() : EntryEntityMapper<UserModel, UserEntity>() {

    override fun transform(entity: UserEntity?): UserModel? {
        val user = UserModel()
        if (entity != null) {
            user.name = entity.name
            user.email = entity.email
        }
        return user

    }

    fun transformList(entityList: List<UserEntity>?): List<UserModel>? {
        return entityList!!.mapTo(ArrayList()) { transform(it)!! }
    }

    fun transform(email: String, password: String): LoginRequestDTO {
        val loginRequestDTO = LoginRequestDTO()
        loginRequestDTO.email = email
        loginRequestDTO.password = password
        return loginRequestDTO
    }

    fun transformRegister(name: String, email: String, password: String, code: String): RegisterRequestDTO {
        val registerRequestDTO = RegisterRequestDTO()
        registerRequestDTO.name = name
        registerRequestDTO.email = email
        registerRequestDTO.password = password
        return registerRequestDTO
    }


}
