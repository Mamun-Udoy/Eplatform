package com.example.eplatform.network.model


import com.google.gson.annotations.SerializedName

data class
UserLoginRequest(
    @SerializedName("email")
    val email: String?,
    @SerializedName("password")
    val password: String?
)