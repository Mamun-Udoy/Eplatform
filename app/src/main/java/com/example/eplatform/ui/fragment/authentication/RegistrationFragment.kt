package com.example.eplatform.ui.fragment.authentication

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.eplatform.R
import com.example.eplatform.databinding.FragmentRegistrationBinding
import com.example.eplatform.network.model.UserRegistrationDataModel
import com.example.eplatform.network.model.UserRegistrationRequest
import com.example.eplatform.utils.navigateTo
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding

    private val registrationViewModel by viewModels<RegistrationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRegistrationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signIn.setOnClickListener {
            navigate()
        }

        binding.signUp.setOnClickListener {
            userSignedUp()
        }

        binding.signIn.setOnClickListener {
            findNavController().navigateTo(R.id.loginFragment)
        }

    }

    private fun userSignedUp() {


        val userInfo = getUserInfoFromViews()

        val validationResult = validateCredentials(
            userInfo.name,
            userInfo.email,
            userInfo.password,
            userInfo.confirmPassword
        )

        if (validationResult.first) {

            registrationViewModel.postRegisterUser(getUserRequest(userInfo))

            registrationViewModel.registrationResult.observe(viewLifecycleOwner) {
                it
                if (it) {
                    findNavController().navigate(R.id.loginFragment)
                    Toast.makeText(
                        requireContext(),
                        "Registration successful. Please log in.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Registration failed. Please try again.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        } else {
            val errorMessage = validationResult.second
            Toast.makeText(requireContext(), "$errorMessage", Toast.LENGTH_SHORT).show()
        }


    }

    private fun getUserRequest(userRegistrationDataModel: UserRegistrationDataModel): UserRegistrationRequest {

        return UserRegistrationRequest(
            avatar = userRegistrationDataModel.avatar,
            email = userRegistrationDataModel.email,
            name = userRegistrationDataModel.name,
            password = userRegistrationDataModel.password
        )

    }

    private fun validateCredentials(
        name: String?,
        email: String?,
        password: String?,
        confirmPassword: String?
    ): Pair<Boolean, String> {

        var result = Pair(true, "")
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(
                confirmPassword
            )
        ) {
            result = Pair(false, " Please Provide information Properly ")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            result = Pair(false, "Please Provide valid Email Address ")

        } else if (password?.length!! < 6) {
            result = Pair(false, "Please Give Password Atleast 6 character")
        } else if (password != confirmPassword) {
            result = Pair(false, "Password Does not match")
        }
        return result

    }

    private fun getUserInfoFromViews(): UserRegistrationDataModel {

        return UserRegistrationDataModel(
            email = binding.userEmail.text.toString(),
            name = binding.userName.text.toString(),
            password = binding.userpassword.text.toString(),
            avatar = "https://picsum.photos/800",
            confirmPassword = binding.userConfirmPassword.text.toString(),
        )

    }

    private fun navigate() {
        findNavController().navigateTo(R.id.loginFragment)
    }

}