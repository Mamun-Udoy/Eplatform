package com.example.eplatform.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.eplatform.R
import com.example.eplatform.databinding.FragmentHomeBinding
import com.example.eplatform.network.paging.LoaderAdapter
import com.example.eplatform.network.paging.PagingAdapter
import com.example.eplatform.network.paging.ProductPagingViewModel
import com.example.eplatform.repository.AppRepo
import com.example.eplatform.utils.isConnectedToInternet
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val productPagingViewModel by viewModels<ProductPagingViewModel>()

    private val pagingAdapter: PagingAdapter by lazy { PagingAdapter() }


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


        }

    }

    private fun fetchData(category: Int = -1) {
        val pagingData = productPagingViewModel.getData(category).distinctUntilChanged()
        lifecycleScope.launch {
            pagingData.collect() {
                pagingAdapter.submitData(it)
            }
        }
    }


}