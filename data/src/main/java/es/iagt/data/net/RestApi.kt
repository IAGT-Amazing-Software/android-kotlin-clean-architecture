package es.iagt.data.net

import es.iagt.data.DataConstants
import es.iagt.data.entity.UserEntity
import es.iagt.data.net.user.UserService
import es.iagt.data.net.user.request.LoginRequestDTO
import es.iagt.data.net.user.request.RegisterRequestDTO
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RestApi @Inject constructor() {

    companion object {
        private const val TIMEOUT = 30L
    }

    private val userService: UserService

    //Headers
    private var language: String = ""

    init {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        httpClient.addInterceptor(logging)
        val retrofit = Retrofit.Builder()
                .client(httpClient.build())
                .baseUrl(DataConstants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        userService = retrofit.create(UserService::class.java)

        language = Locale.getDefault().country
    }


    fun login(loginRequestDTO: LoginRequestDTO): Observable<UserEntity> {
        return userService.login(loginRequestDTO)
    }

    fun register(registerRequestDTO: RegisterRequestDTO): Observable<UserEntity> {
        return userService.register(registerRequestDTO)
    }
}
