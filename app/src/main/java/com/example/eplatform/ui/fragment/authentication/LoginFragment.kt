package com.example.eplatform.ui.fragment.authentication

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.eplatform.R
import com.example.eplatform.databinding.FragmentLoginBinding
import com.example.eplatform.ui.activity.ApplicationActivity
import com.example.eplatform.utils.navigateTo
import dagger.hilt.android.AndroidEntryPoint
import java.text.Bidi

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val loginViewModel by viewModels<LoginViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //jumping to registration page by signup button
        binding.signUp.setOnClickListener {
            navigate()
        }

        //user trying to login
        binding.login.setOnClickListener {
            userLoggedIn()
        }


    }

    private fun userLoggedIn() {
        checkLoginCredentialValidity()
    }

    private fun navigate() {
        findNavController().navigateTo(R.id.registrationFragment)
    }

    private fun checkLoginCredentialValidity() {

        val userEmail = binding.userEmail.text.toString()
        val userPassword = binding.userpassword.text.toString()

        Log.d("pressed", "checkLoginCredentialValidity: ")

        val validationResult = validateCredential(userEmail, userPassword)

        if (validationResult.first) {

            loginViewModel.getUserInfo(userEmail, userPassword)
            handleToast()


        } else {


            val errorMessage = validationResult.second
            Log.d("tag", "checkLoginCredentialValidity: $errorMessage  ")
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()

        }


    }

    private fun handleToast() {
        loginViewModel.loginResult.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {

                if(it!="Unauthorized"){
                    Log.d("successful", "so network call is valid  ${loginViewModel.loginResult} ")
                    //findNavController().navigateTo(R.id.homeFragment)
                    val intent = Intent(requireContext(), ApplicationActivity::class.java)
                    startActivity(intent)


                }
                else{
                    Toast.makeText(requireContext(), "User is Invalid", Toast.LENGTH_SHORT).show()
                    Log.d("tag2", "udoy is not registered  ${loginViewModel.loginResult} ")

                }

            } else {
                Toast.makeText(requireContext(), "User is Invalid", Toast.LENGTH_SHORT).show()
                Log.d("tag2", "user is not registered  ${loginViewModel.loginResult} ")
            }
        }
    }

    private fun validateCredential(userEmail: String, userPassword: String): Pair<Boolean, String> {

        var result = Pair(true, "")
        if (userPassword.length < 6) {
            result = Pair(false, " Password did not match")
        }
        if (TextUtils.isEmpty(userEmail) and !TextUtils.isEmpty(userPassword)) {
            result = Pair(false, " Please Provide userEmail ")
        } else if (TextUtils.isEmpty(userPassword) and !TextUtils.isEmpty(userEmail)) {
            result = Pair(false, " Please Provide userEmail ")
        } else if (TextUtils.isEmpty(userEmail) or TextUtils.isEmpty(userPassword)) {
            result = Pair(false, " Please Provide userEmail and Password ")
        }
        return result


    }


}