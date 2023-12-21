package com.example.eplatform.error_model


import com.google.gson.annotations.SerializedName

data class SignupExceptionResponse(
    @SerializedName("error")
    val error: String?,
    @SerializedName("message")
    val message: List<String?>?,
    @SerializedName("statusCode")
    val statusCode: Int?
)