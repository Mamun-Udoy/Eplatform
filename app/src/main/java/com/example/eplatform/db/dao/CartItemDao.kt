package com.example.eplatform.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.eplatform.db.entities.CartItemEntity
import com.example.eplatform.db.entities.WishListEntity

@Dao
interface CartItemDao {

    @Query("SELECT COUNT(id) FROM cartItem")
    fun getCartItemsSize(): Int

    @Delete
    fun deleteCartItem(cartItemEntity: CartItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCartItem(cartItemEntity: CartItemEntity)

    @Query(value = "SELECT * FROM cartItem ")
    fun getCartProductItem(): List<CartItemEntity>?

    @Update
    fun updateItem(item: CartItemEntity)

}