package com.example.eplatform.ui.fragment.authentication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eplatform.error_model.SignupExceptionResponse
import com.example.eplatform.network.model.UserRegistrationRequest
import com.example.repository.AppRepo
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import javax.inject.Inject


@HiltViewModel
class RegistrationViewModel @Inject constructor(private var appRepo: AppRepo) : ViewModel() {

    private val _registrationResult = MutableLiveData<Boolean>()
    val registrationResult: LiveData<Boolean> = _registrationResult

    fun postRegisterUser(userRegistrationRequest: UserRegistrationRequest) = viewModelScope.launch {


        Log.d("username", "print username ${userRegistrationRequest.name}")

//        val url = "https://fakestoreapi.com/users"

        val userRegistrationRequest = userRegistrationRequest

        try {
            val response = appRepo.userSignUp(
                url = "https://api.escuelajs.co/api/v1/users/",
                userRegistrationRequest
            )
            if (response.isSuccessful) _registrationResult.value = true
            else {
                _registrationResult.value = false
                val errorResponse = Gson().fromJson(response.errorBody()?.stringSuspending(), SignupExceptionResponse::class.java)
                Log.d("error_signUp", "getUserInfo: errorCode: ${Gson().toJson(errorResponse)}")

//                Log.d(
//                    "error_userregistration",
//                    "UserRegistrationInfoError: error: error code: $code, $error"
//                )
            }

            Log.d("registration_result", "postRegisterUser: ${registrationResult.value} ")

        } catch (exp: Exception) {
            Log.d("login_error", "getUserInfo: error: ${exp.localizedMessage}")
        }
    }
}

