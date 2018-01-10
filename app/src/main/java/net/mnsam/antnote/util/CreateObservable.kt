package net.mnsam.antnote.util

import io.reactivex.Observable
import java.util.concurrent.Callable

/**
 * Created by Mochamad Noor Syamsu on 1/9/18.
 */
class CreateObservable {
    fun <T> observable(callable: Callable<T>): Observable<T> {
        return Observable.create { e ->
            e.onNext(callable.call())
            e.onComplete()
        }
    }
}