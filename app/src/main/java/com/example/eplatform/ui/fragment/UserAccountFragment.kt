package com.example.eplatform.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.eplatform.R
import com.example.eplatform.adapter.ViewPagerAdapter
import com.example.eplatform.databinding.FragmentUserAccountBinding
import com.example.eplatform.ui.activity.MainActivity
import com.example.eplatform.utils.session.SharedPreferencesManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class UserAccountFragment : Fragment() {

    private lateinit var binding: FragmentUserAccountBinding

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2

    private lateinit var myadapter: ViewPagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUserAccountBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myadapter = ViewPagerAdapter(childFragmentManager, lifecycle)

        viewPager2 = binding.viewPager
        tabLayout = binding.tabLayout

        // Set up ViewPager2 with the adapter
        viewPager2.adapter = myadapter

        // Use TabLayoutMediator to link TabLayout with ViewPager2
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> tab.text = "Wishlist"
                1 -> tab.text = "Order History"
            }
        }.attach()
        val sharedPreferencesManager = SharedPreferencesManager(requireContext())
        binding.logouticon.setOnClickListener {
            sharedPreferencesManager.clearLoggedIn()
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()

        }


    }


}