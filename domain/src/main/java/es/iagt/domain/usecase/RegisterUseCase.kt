package es.iagt.domain.usecase

import es.iagt.data.model.UserModel
import es.iagt.data.repository.user.UserRepository
import es.iagt.domain.executor.PostExecutionThread
import io.reactivex.Observable
import javax.inject.Inject


class RegisterUseCase
@Inject
constructor(private val userRepository: UserRepository,
            postExecutionThread: PostExecutionThread) : UseCase<UserModel, RegisterUseCase.Params>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Observable<UserModel> {
        return this.userRepository.register(params.name, params.email, params.password, params.code)
    }

    class Params private constructor(val name: String, val email: String, val password: String, val code: String) {
        companion object {
            fun set(name: String, email: String, password: String, code: String): Params {
                return Params(name, email, password, code)
            }
        }
    }

}
