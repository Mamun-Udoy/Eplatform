package com.example.eplatform.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.eplatform.db.entities.CartItemEntity
import com.example.eplatform.db.entities.WishListEntity
import com.example.eplatform.repository.DatabaseRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val dbRepo: DatabaseRepo
) : ViewModel() {
    val totalCostLiveData = MutableLiveData<Int>()
    val count = MutableLiveData<Int>()
    val cartProductItemLiveData = MutableLiveData<List<CartItemEntity>>()
    fun getCartItemSize(): Int {
        return dbRepo.getCartItemSize()
    }

    fun getCartProductItem() {
        val data = dbRepo.getCartProductItem() ?: emptyList()
        cartProductItemLiveData.value = data
        totalCostLiveData.value = totalCost(data)
    }


    fun insertCartItem(cartItemEntity: CartItemEntity) {
        dbRepo.insertCartItem(cartItemEntity)
    }

    fun deleteCartItem(cartItemEntity: CartItemEntity) {
        dbRepo.deleteCartItem(cartItemEntity)

    }


    fun updateCartItem(cartItemEntity: CartItemEntity) {
        dbRepo.getCartUpdatedItem(cartItemEntity)
    }

    private fun totalCost(dataList: List<CartItemEntity>): Int {
        var totalCost = 0
        for (item in dataList) {
            totalCost += item.price?.times(item.qty) ?: 0
            Log.d("checksum", "totalCost: $totalCost")
        }
        return totalCost
    }
    fun getEachItemCount(id: Int): Int {
        return dbRepo.getEachCartItemCount(id)
    }


}