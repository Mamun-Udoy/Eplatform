package com.example.eplatform.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eplatform.R
import com.example.eplatform.adapter.BaseAdapter
import com.example.eplatform.adapter.WishListAdapter
import com.example.eplatform.databinding.FragmentWishListBinding
import com.example.eplatform.db.entities.WishListEntity
import com.example.eplatform.ui.viewmodel.WishListViewModel


class WishListFragment : Fragment() {

    private lateinit var binding: FragmentWishListBinding
    private val wishListAdapter by lazy { WishListAdapter(R.layout.item_wish_list) }

    private val viewModel by viewModels<WishListViewModel>()

    private fun init() {
        binding.wishListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.wishListRecyclerView.adapter = wishListAdapter
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWishListBinding.inflate(inflater)
        return binding.root;


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        getData()
    }

    private fun getData() {
        viewModel.getProductItem().observe(viewLifecycleOwner) {
            Log.d("adapterGetData", "get the data from retro $it")
            wishListAdapter.addNewList(it)
        }



    }
}