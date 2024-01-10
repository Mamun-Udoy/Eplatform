package com.example.eplatform.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eplatform.db.entities.ProductEntity
import com.example.eplatform.repository.DatabaseRepo
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dbRepo: DatabaseRepo
) : ViewModel() {

    fun insertProductItem(productEntity: ProductEntity){
        Log.d("database_db", "insertProductItem: ${Gson().toJson(productEntity)}" )
        dbRepo.insertProductItem(productEntity)

    }



    fun getProductItem(): List<ProductEntity>? {
        return dbRepo.getProductItem()
        Log.d("offline_product", "readProductItem: ${dbRepo.getProductItem()}" )
    }



}