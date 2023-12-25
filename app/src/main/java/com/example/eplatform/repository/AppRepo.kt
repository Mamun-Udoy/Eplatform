package com.example.eplatform.repository

import com.example.eplatform.network.ApiInterface
import com.example.eplatform.network.model.UserLoginRequest
import com.example.eplatform.network.model.UserRegistrationRequest
import javax.inject.Inject

class AppRepo @Inject constructor(
    private val api: ApiInterface
) {

    suspend fun userLogin(url: String, userLoginRequest: UserLoginRequest) = api.login(userLoginRequest)

    suspend fun userSignUp(url: String, userSignUpRequest: UserRegistrationRequest) = api.signup(url,userSignUpRequest)

    suspend fun getProductItem(offset: Int, limit: Int) = api.getProducts(offset,limit)

    suspend fun getProductsNew(offset: Int,limit: Int) = api.getProductsNew(offset = offset,limit =  limit)

    suspend fun getCategoryWiseProduct(categoryId:Int) = api.getCategoryWiseProduct(url = "https://api.escuelajs.co/api/v1/categories/$categoryId/products")
}