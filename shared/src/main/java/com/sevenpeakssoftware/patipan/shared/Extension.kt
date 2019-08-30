package com.sevenpeakssoftware.patipan.shared

import com.google.gson.Gson
import com.sevenpeakssoftware.patipan.model.ErrorWrapperApi
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

inline fun <reified T> String.parseToClass(): T {
    return Gson().fromJson<T>(this, T::class.java)
}

inline fun <reified T> ResponseBody?.getErrorFormApi(): ApiException {
    return try {
        (this?.string()?.parseToClass<T>() as ErrorWrapperApi).getErrorMessage()
    } catch (e: Exception) {
        e.printStackTrace()
        ApiException(999, e.localizedMessage)
    }
}

fun getResultError(code: Int, message: String?): ApiException {
    return ApiException(code, "$message [$code]")
}

fun Throwable.getResultFailed(): ResultFailed {
    return when (this) {
        is SocketTimeoutException -> {
            ResultFailed(Throwable())
        }

        is IOException -> {
            ResultFailed(Throwable())
        }

        is HttpException -> {
            val errorWrapper = response().errorBody().getErrorFormApi<ErrorWrapperApi>()
            ResultFailed(getResultError(errorWrapper.code, errorWrapper.message))
        }

        else -> {
            ResultFailed(this)
        }
    }
}