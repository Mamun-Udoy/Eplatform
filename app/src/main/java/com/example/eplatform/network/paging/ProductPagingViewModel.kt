package com.example.eplatform.network.paging

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.eplatform.repository.AppRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductPagingViewModel @Inject constructor(private val appRepo: AppRepo): ViewModel(){

    val loading = MutableLiveData<Boolean>()

    val productList = Pager(PagingConfig(1)){
        PagingSource(appRepo)
    }.flow.cachedIn(viewModelScope)
}