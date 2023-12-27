package com.example.eplatform.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eplatform.R
import com.example.eplatform.databinding.FragmentUserAccountBinding


class UserAccountFragment : Fragment() {

    private lateinit var binding: FragmentUserAccountBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUserAccountBinding.inflate(inflater)
        return binding.root
    }


}