package com.example.eplatform.ui.fragment.authentication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eplatform.error_model.LoginExceptionResponse
import com.example.eplatform.network.model.UserLoginRequest
import com.example.eplatform.repository.AppRepo
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val appRepo: AppRepo) : ViewModel() {


    private val _loginResult = MutableLiveData<String>()
    var loginResult: LiveData<String> = _loginResult




    fun getUserInfo(userEmail: String, userPassWord: String) = viewModelScope.launch {

        val url = "https://api.escuelajs.co/api/v1/auth/login"
        val userLoginResponse = UserLoginRequest(userEmail, userPassWord)

        try {
            val response = appRepo.userLogin(url, userLoginResponse)

            if (response.isSuccessful) {
                val token = response.body()?.accessToken ?: ""
                _loginResult.value = token
                Log.d("tokeen_db", "getUserInfo: token: $token")

            } else {


                val errorResponse = Gson().fromJson(response.errorBody()?.stringSuspending(), LoginExceptionResponse::class.java)
                Log.d("error_login", "getUserInfo: errorCode: ${errorResponse.statusCode}")
                Log.d("error_login", "getUserInfo: errorMessage: ${errorResponse.message}")



                _loginResult.value = errorResponse.message
                Log.d("loginresult", "loginResult: ${loginResult.value}")
            }
        } catch (exp: Exception) {
            Log.d("login_error", "getUserInfo: error: ${exp.localizedMessage}")
        }


    }


}

@Suppress("BlockingMethodInNonBlockingContext")
suspend fun ResponseBody.stringSuspending() =
    withContext(Dispatchers.IO) { string() }