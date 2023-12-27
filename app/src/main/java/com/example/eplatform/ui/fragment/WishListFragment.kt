package com.example.eplatform.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eplatform.R
import com.example.eplatform.databinding.FragmentWishListBinding


class WishListFragment : Fragment() {

    private lateinit var binding: FragmentWishListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWishListBinding.inflate(inflater)
        return binding.root
    }


}