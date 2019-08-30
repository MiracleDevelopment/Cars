package com.sevenpeakssoftware.patipan.model
import com.google.gson.annotations.SerializedName


data class ErrorWrapperApi(
    @SerializedName("errors")
    val errors: Errors?,
    @SerializedName("exception")
    val exception: Exception?,
    @SerializedName("message")
    val message: String?, // The given data was invalid.
    @SerializedName("status_code")
    val statusCode: Int? // 422
)

data class Errors(
    @SerializedName("message")
    val message: String?, // Page not Found
    @SerializedName("status_code")
    val statusCode: Int?, // 404
    @SerializedName("errors")
    val errors: Map<*,*>? // he requested resource failed authorization
)

data class Exception(
    @SerializedName("code")
    val code: Int?, // 0
    @SerializedName("file")
    val fileString: String?,
    @SerializedName("line")
    val line: Int?, // 231
    @SerializedName("message")
    val message: String?,
    @SerializedName("trace")
    val trace: String?
)
