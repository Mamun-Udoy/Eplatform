package com.example.eplatform.repository

import com.example.eplatform.db.ProductDatabase
import com.example.eplatform.db.entities.CartItemEntity
import com.example.eplatform.db.entities.ProductEntity
import com.example.eplatform.db.entities.WishListEntity
import javax.inject.Inject

class DatabaseRepo @Inject constructor(
    private val db: ProductDatabase
) {
    suspend fun getProductItem() = db.productItemDao().getProductItem()

    fun insertProductItem(productEntity: ProductEntity) =
        db.productItemDao().insertProductItem(productEntity)

    suspend fun deleteProductitem(productEntity: ProductEntity) =
        db.productItemDao().deleteProductItem(productEntity)

    fun getTotalProductItemSize() = db.productItemDao().getProductItemsSize()


    //wishList dao implementation
    suspend fun getWishListItem() = db.WishListItemDao().getProductItem()

    fun deleteWishListItem(wishListEntity: WishListEntity) =
        db.WishListItemDao().deleteProductItem(wishListEntity)

    fun insertWishListItem(wishListEntity: WishListEntity) =
        db.WishListItemDao().insertWishListItem(wishListEntity)


    //carItem dao implementation

    fun insertCartItem(cartItemEntity: CartItemEntity) =
        db.CartItemDao().insertCartItem(cartItemEntity)

    fun deleteCartItem(cartItemEntity: CartItemEntity) =
        db.CartItemDao().deleteCartItem(cartItemEntity)

    fun getCartItemSize() = db.CartItemDao().getCartItemsSize()

    fun getCartUpdatedItem(cartItemEntity: CartItemEntity) = db.CartItemDao().updateItem(cartItemEntity)

    fun getCartProductItem()=db.CartItemDao().getCartProductItem()

    fun getEachCartItemCount(id:Int)=db.CartItemDao().getEachItemCount(id)


}