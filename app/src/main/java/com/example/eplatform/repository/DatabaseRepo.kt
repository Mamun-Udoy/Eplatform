package com.example.eplatform.repository

import com.example.eplatform.db.ProductDatabase
import com.example.eplatform.db.entities.ProductEntity
import javax.inject.Inject

class DatabaseRepo @Inject constructor (
    private val db: ProductDatabase
) {
    suspend fun getProductItem() = db.productItemDao().getProductItem()

    fun insertProductItem(productEntity: ProductEntity) = db.productItemDao().insertProductItem(productEntity)

    suspend fun deleteProductitem(productEntity: ProductEntity)= db.productItemDao().deleteProductItem(productEntity)

    suspend fun getTotalProductItemSize()= db.productItemDao().getProductItemsSize()
}