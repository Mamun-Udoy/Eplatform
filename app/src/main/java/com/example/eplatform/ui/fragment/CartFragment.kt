package com.example.eplatform.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eplatform.adapter.CartAdaptar
import com.example.eplatform.databinding.FragmentCartBinding
import com.example.eplatform.db.entities.CartItemEntity
import com.example.eplatform.ui.viewmodel.CartViewModel
import com.example.eplatform.ui.viewmodel.WishListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment  : Fragment(), CartAdaptar.OnItemClickListener {


    private lateinit var binding: FragmentCartBinding
    var count = Int

    private val cartAdapter by lazy { CartAdaptar(this) }

    private val viewModel by viewModels<WishListViewModel>()

    private val cartViewModel by viewModels<CartViewModel>()



    private fun init() {
        binding.cartItemRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.cartItemRecyclerView.adapter = cartAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getData()
        observeTotalCost()
    }

    private fun getData() {
        cartViewModel.cartProductItemLiveData.observe(viewLifecycleOwner) {
            Log.d("adapterGetData", "get the data from retro $it")
            cartAdapter.addNewList(it)
        }
       cartViewModel.getCartProductItem()



    }
    private fun observeTotalCost() {
        cartViewModel.totalCostLiveData.observe(viewLifecycleOwner) {
            binding.totalCost.text = "Total = $it"
        }
    }

    override fun onItemDeleted(item: CartItemEntity, position: Int) {
        cartViewModel.deleteCartItem(item)
        getData()
    }

    override fun updateItem(item: CartItemEntity) {
        cartViewModel.updateCartItem(item)
        getData()
    }

    override fun countUpdater(item: CartItemEntity) {


        Log.d("eachItemCount", "countUpdater:   ${cartViewModel.getEachItemCount(item.id!!)}")

//        count = cartViewModel.getEachItemCount(item.id!!)
//        binding.cartItemRecyclerView.


    }


}