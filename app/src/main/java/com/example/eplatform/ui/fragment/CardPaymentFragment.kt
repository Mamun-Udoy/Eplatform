package com.example.eplatform.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eplatform.R
import com.example.eplatform.databinding.FragmentCardPaymentBinding
import com.example.eplatform.databinding.FragmentPaymentBinding


class CardPaymentFragment : Fragment() {

    private lateinit var binding: FragmentCardPaymentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardPaymentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}