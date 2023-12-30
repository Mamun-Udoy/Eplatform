package com.example.eplatform.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.eplatform.db.entities.WishListEntity
import com.example.eplatform.repository.DatabaseRepo
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WishListViewModel @Inject constructor(
    private val dbrepo: DatabaseRepo
) : ViewModel() {

    fun getProductItem() = liveData(
        Dispatchers.IO,

    ) {
        val data = dbrepo.getWishListItem()
        emit(data)
    }

    fun insertWishListItem(wishListEntity: WishListEntity){

        dbrepo.insertWishListItem(wishListEntity)

    }
}