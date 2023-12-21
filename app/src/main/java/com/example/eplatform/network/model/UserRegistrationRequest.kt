package com.example.eplatform.network.model


import com.google.gson.annotations.SerializedName

data class UserRegistrationRequest(
    @SerializedName("avatar")
    val avatar: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("password")
    val password: String?
)