package com.example.eplatform.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.eplatform.R
import com.example.eplatform.databinding.ActivityApplicationBinding

class ApplicationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityApplicationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityApplicationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigationView.background = null


    }
}