package com.example.eplatform.repository

import com.example.db.entities.ProductDatabase
import javax.inject.Inject

class DatabaseRepo @Inject constructor (
    private val db: ProductDatabase
) {
    suspend fun getProductItem() = db.productItemDao().getProductItem()
}