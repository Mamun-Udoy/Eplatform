package com.example.eplatform.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.eplatform.R
import com.example.eplatform.databinding.FragmentCheckOutBinding

class CheckOutFragment : Fragment() {

    private lateinit var binding: FragmentCheckOutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheckOutBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.placeOrder.setOnClickListener {
            findNavController().navigate(R.id.cardPaymentFragment)
        }
    }


}