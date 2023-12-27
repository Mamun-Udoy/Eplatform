package com.example.eplatform.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.eplatform.R
import com.example.eplatform.databinding.ActivityApplicationBinding
import com.example.eplatform.utils.navigateTo
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ApplicationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityApplicationBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityApplicationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.appfragment)

        bottombar()


        /* note
        * here i have two activity
        * after authentication we reached on application activity and application activity contain topbar, bottombar, and in between topbar nad bottombar it contains a layout that holding the container where we will replace our fragments
        * so here has a problem sometimes android ide will occur rendering problem and will tell you to use FragmentContainerView
        * if we use this no error will give but inside this FragmentContainerView we will give an id and using this id reference we will give it to our navigation host id so navigation can understand its start point
        * but here using FragmentContainerView it will give an error when you will go to navigate. it will give a error navigation is not set into a particular fragment
        * so clearing this bug we have to use Fragment inside the layout instead of FragmentContainerView
        */
    }


    private fun bottombar() {

        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    navController.navigateTo(R.id.homeFragment)
                    true
                }


                R.id.person -> {
                    navController.navigate(R.id.userAccountFragment)
                    true
                }

                else -> false
            }
        }


    }
}