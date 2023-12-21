package com.example.eplatform.network

import com.example.eplatform.network.model.UserLoginRequest
import com.example.eplatform.network.model.UserLoginResponse
import com.example.eplatform.network.model.UserRegistrationRequest
import com.example.eplatform.network.model.UserRegistrationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

interface ApiInterface {

    @POST
    suspend fun login(
        @Url url: String?,
        @Body userLoginRequest: UserLoginRequest?
    ): Response<UserLoginResponse>


    @POST
    suspend fun signup(
        @Url url:String?,
        @Body userSignUpRequest: UserRegistrationRequest?

    ):Response<UserRegistrationResponse>
}