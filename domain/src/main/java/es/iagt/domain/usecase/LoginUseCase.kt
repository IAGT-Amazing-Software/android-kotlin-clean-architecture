package es.iagt.domain.usecase

import es.iagt.data.model.UserModel
import es.iagt.data.repository.user.UserRepository
import es.iagt.domain.executor.PostExecutionThread
import io.reactivex.Observable
import javax.inject.Inject


class LoginUseCase
@Inject
constructor(private val userRepository: UserRepository,
            postExecutionThread: PostExecutionThread) : UseCase<UserModel, LoginUseCase.Params>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Observable<UserModel> {
        return this.userRepository.login(params.email, params.password)
    }

    class Params private constructor(val email: String, val password: String) {
        companion object {
            fun set(email: String, password: String): Params {
                return Params(email, password)
            }
        }
    }

}
