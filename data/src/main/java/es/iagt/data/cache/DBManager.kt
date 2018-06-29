package es.iagt.data.cache

import es.iagt.data.entity.UserEntity
import io.reactivex.Observable
import javax.inject.Inject


class DBManager
@Inject constructor(appDatabase: AppDatabase) {

    private val userDao = appDatabase.userDao()

    fun saveUser(userEntity: UserEntity) {
        userDao.insertAll(userEntity)
    }

    fun updateUser(userEntity: UserEntity) {
        userDao.updateUser(userEntity)
    }

    fun deleteUser() {
        userDao.deleteAll()
    }

    fun getUser(): Observable<UserEntity> {
        return Observable.create { subscriber ->
            val user: UserEntity? = userDao.findById(0)
            if (user == null){
                subscriber.onError(Throwable())
            }else{
                subscriber.onNext(user)
                subscriber.onComplete()
            }
        }
    }
}


