package com.sevenpeakssoftware.patipan.shared

import com.sevenpeakssoftware.patipan.model.ErrorWrapperApi
import java.lang.Exception

sealed class Result<out T>
data class ResultSuccess<T>(val item: T) : Result<T>()
data class ResultFailed(val failed: Throwable, val code: Int? = null) : Result<Nothing>()
object ResultLoading : Result<Nothing>()
object ResultLoaded : Result<Nothing>()


inline fun <T> Result<T>.success(
    success: (T) -> Unit,
    noinline fail: ((Throwable) -> Unit)? = null,
    noinline loading: (() -> Unit)? = null,
    noinline loaded: (() -> Unit)? = null
) {
    when (this) {
        is ResultSuccess<T> -> {
            success.invoke(item)
        }
        is ResultFailed -> {
            fail?.invoke(failed)
        }
        is ResultLoading -> {
            loading?.invoke()
        }
        is ResultLoaded -> {
            loaded?.invoke()
        }
    }
}

inline fun <T> Result<T>.value(): T? {
    return when (this) {
        is ResultSuccess<T> -> {
            item
        }
        else -> null
    }
}

fun ErrorWrapperApi.getErrorMessage(): ApiException {
    return when {
        this.message != null -> ApiException(this.statusCode ?: 0, this.message ?: "")
        this.errors != null -> ApiException(this.errors?.statusCode ?: 0, this.errors?.message ?: "")
        this.errors?.errors != null -> ApiException(this.errors?.statusCode ?: 0, this.errors?.errors?.values?.toString() ?: "")
        else -> ApiException(990, "Can't get error message")
    }
}

fun Exception.getErrorMessage(): ApiException = ApiException(995, this.localizedMessage)

class ApiException(val code: Int, val desc: String) : Exception(desc)
class ServerException(val apiCode: Int, val desc: String) : Exception(desc)


