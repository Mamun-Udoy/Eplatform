package com.example.eplatform.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.eplatform.db.entities.ProductEntity

@Dao
interface ProductItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProductItem(productEntity: ProductEntity)
    @Delete
    suspend fun deleteProductItem(productEntity: ProductEntity)
    @Query(value = "SELECT * FROM productitem ")
    suspend fun getProductItem(): List<ProductEntity>?
    @Query("SELECT COUNT(id) FROM productitem")
    fun getProductItemsSize(): Int
    @Query("SELECT * FROM productitem ")
    suspend fun getPagingSource(): List<ProductEntity>

    //for deleting all item
    @Query("DELETE FROM productitem")
    fun deleteAllCheckoutItems()


}