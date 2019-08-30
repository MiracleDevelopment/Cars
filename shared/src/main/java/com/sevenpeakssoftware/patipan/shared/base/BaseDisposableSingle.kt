package com.sevenpeakssoftware.patipan.shared.base


import com.sevenpeakssoftware.patipan.shared.ResultFailed
import com.sevenpeakssoftware.patipan.shared.ResultSuccess
import com.sevenpeakssoftware.patipan.shared.getResultFailed
import io.reactivex.observers.DisposableSingleObserver
import com.sevenpeakssoftware.patipan.shared.Result

abstract class BaseDisposableSingle<T> : DisposableSingleObserver<T>() {

    override fun onSuccess(t: T) {
        onSuccess(ResultSuccess(t))
    }

    override fun onError(e: Throwable) {
        val error = e.getResultFailed()
        onError(error.failed, ResultFailed(error.failed))
    }

    abstract fun onSuccess(success: Result<T>)

    abstract fun onError(e: Throwable, error: Result<T>)
}