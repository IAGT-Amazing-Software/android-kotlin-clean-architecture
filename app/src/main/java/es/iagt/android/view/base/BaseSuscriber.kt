package es.iagt.android.view.base

import io.reactivex.observers.DisposableObserver


open class BaseSubscriber<T> : DisposableObserver<T>() {

    override fun onError(e: Throwable) {
    }

    override fun onComplete() {
    }

    override fun onNext(response: T) {
    }
}
