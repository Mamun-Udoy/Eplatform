package com.example.eplatform.paging

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.eplatform.db.entities.ProductEntity
import com.example.eplatform.repository.AppRepo
import com.example.eplatform.repository.DatabaseRepo
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductPagingViewModel @Inject constructor(
    private val appRepo: AppRepo,
    private val dbRepo: DatabaseRepo
) : ViewModel() {


    fun getData(category: Int) = Pager(PagingConfig(1)) {
        PagingSource(appRepo,dbRepo,category)
    }.flow.cachedIn(viewModelScope)

    fun insertProductItem(productEntity: ProductEntity) = viewModelScope.launch{
        Log.d("database_db", "insertProductItem: ${Gson().toJson(productEntity)}" )
        dbRepo.insertProductItem(productEntity)
    }

    fun executeProducts() {
        viewModelScope.launch {
            val data = appRepo.getProductsNew(offset = 0,10)


        }
    }


//    fun getData(category: Int = -1 ) = Pager(
//        config = PagingConfig(pageSize =5, maxSize = 25),
//        pagingSourceFactory = { PagingSource(appRepo,category) }
//    ).flow.cachedIn(viewModelScope)


}