package com.example.eplatform.utils

sealed class ApiServiceResponse<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : ApiServiceResponse<T>(data = data)
    class Error<T>(errorMessage: String) : ApiServiceResponse<T>(message = errorMessage)
    class Loading<T> : ApiServiceResponse<T>()
}

