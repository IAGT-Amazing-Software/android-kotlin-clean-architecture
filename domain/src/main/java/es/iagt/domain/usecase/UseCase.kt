package es.iagt.domain.usecase

import android.annotation.SuppressLint
import android.support.v4.util.Preconditions
import es.iagt.domain.executor.PostExecutionThread
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers


abstract class UseCase<T, in Params> protected constructor(private val postExecutionThread: PostExecutionThread) {

    private val disposables: CompositeDisposable = CompositeDisposable()

    protected abstract fun buildUseCaseObservable(params: Params): Observable<T>

    @SuppressLint("RestrictedApi")
    fun execute(observer: DisposableObserver<T>, params: Params) {

        Preconditions.checkNotNull(observer)
        val observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(postExecutionThread.scheduler)
                .unsubscribeOn(Schedulers.io())
        addDisposable(observable.subscribeWith(observer))
    }

    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    @SuppressLint("RestrictedApi")
    private fun addDisposable(disposable: Disposable) {
        Preconditions.checkNotNull(disposable)
        Preconditions.checkNotNull(disposables)
        disposables.add(disposable)
    }

}
