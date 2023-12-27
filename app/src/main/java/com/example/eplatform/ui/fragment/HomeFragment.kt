package com.example.eplatform.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.paging.map
import androidx.recyclerview.widget.GridLayoutManager
import com.example.eplatform.R
import com.example.eplatform.databinding.FragmentHomeBinding
import com.example.eplatform.network.model.ProductResponse
import com.example.eplatform.paging.ItemClickListener
import com.example.eplatform.paging.LoaderAdapter
import com.example.eplatform.paging.PagingAdapter
import com.example.eplatform.paging.ProductPagingViewModel
import com.example.eplatform.ui.viewmodel.HomeViewModel
import com.example.eplatform.utils.isConnectedToInternet
import com.example.eplatform.utils.toProductEntity
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment(), ItemClickListener {

    private lateinit var binding: FragmentHomeBinding

    private val productPagingViewModel by viewModels<ProductPagingViewModel>()

    private val pagingAdapter: PagingAdapter by lazy { PagingAdapter(this) }

    private val homeViewModel by viewModels<HomeViewModel>()


    private fun init() {
        binding.productItemRecyclerview.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = pagingAdapter.withLoadStateHeaderAndFooter(
                header = LoaderAdapter(),
                footer = LoaderAdapter()
            )
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (context?.isConnectedToInternet() == true) {
            init()
            fetchData()
            Log.d("database_db", "fetchData: paging data fetching...")
        }

    }

    private fun fetchData(category: Int = -1) {
        val pagingData = productPagingViewModel.getData(category).distinctUntilChanged()
        lifecycleScope.launch {
            pagingData.collect {

//                cachedData(it)
                Log.d("database_db", "fetchData: paging data found: $it")
                pagingAdapter.submitData(it)

            }
        }
    }

    private fun cachedData(pagingData: PagingData<ProductResponse.ProductResItem>) {
        Log.d("database_db", "fetchData: item data -> ")

        pagingData.map() {
            val itemEntity = it.toProductEntity()
            Log.d("database_db", "fetchData: item data2 -> $itemEntity")
            homeViewModel.insertProductItem(itemEntity)
        }

    }

    override fun onItemBuyNowClicked(item: ProductResponse.ProductResItem?) {
        val bundle = Bundle()
        bundle.putString("data_item", Gson().toJson(item))

        findNavController().navigate(R.id.productDetailsFragment, bundle)

//        requireView().findNavController().navigate(R.id.productDetailsFragment, bundle)
    }

    override fun wishListClicked(item: ProductResponse.ProductResItem?) {

    }


}