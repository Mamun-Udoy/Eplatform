package com.example.eplatform.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.example.eplatform.db.entities.WishListEntity


@Dao
interface WishListItemDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWishListItem(wishListEntity: WishListEntity)
    @Delete
    suspend fun deleteProductItem(wishListEntity: WishListEntity)

    @Query(value = "SELECT * FROM wishlistitem ")
    suspend fun getProductItem(): List<WishListEntity>?


}