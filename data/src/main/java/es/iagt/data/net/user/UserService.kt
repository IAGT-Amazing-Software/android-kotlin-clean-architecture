package es.iagt.data.net.user

import es.iagt.data.entity.UserEntity
import es.iagt.data.net.user.request.LoginRequestDTO
import es.iagt.data.net.user.request.RegisterRequestDTO
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface UserService {

    @POST("api/login")
    @Headers("Content-Type: application/json")
    fun login(@Body loginRequestDTO: LoginRequestDTO): Observable<UserEntity>

    @POST("api/signIn")
    @Headers("Content-Type: application/json")
    fun register(@Body registerRequestDTO: RegisterRequestDTO): Observable<UserEntity>



}
