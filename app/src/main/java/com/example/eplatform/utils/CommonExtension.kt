package com.example.eplatform.utils

import android.content.Context
import android.net.ConnectivityManager
import androidx.navigation.NavController
import com.example.eplatform.db.entities.CartItemEntity
import com.example.eplatform.db.entities.ProductEntity
import com.example.eplatform.db.entities.WishListEntity
import com.example.eplatform.network.model.ProductResponse

fun NavController.navigateTo(destinationResid: Int) {

    if (currentDestination == null) {
        navigate(destinationResid)
    } else {
        currentDestination?.let {
            if (it.id != destinationResid) {
                navigate(destinationResid)
            }
        }
    }

}


fun Context.isConnectedToInternet(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetworkInfo

    return activeNetwork?.isConnected == true
}

fun ProductEntity.toProductResponse(): ProductResponse.ProductResItem {
    return ProductResponse.ProductResItem(
        id = id,
        description = description,
        price = price,
        title = title,
        images = listOf(image),
        category = null,
        creationAt = String(),
        updatedAt = String()
    )

}

fun ProductResponse.ProductResItem.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        description = description,
        price = price,
        title = title,
        categoryId = category?.id,
        image = images[0],
        name = category?.name
    )
}

fun ProductResponse.ProductResItem.toWishListEntity(): WishListEntity {
    return WishListEntity(
        id = id,
        description = description,
        price = price,
        title = title,
        categoryId = category?.id,
        image = images?.get(0) ?: "",
        name = category?.name
    )
}


fun WishListEntity.toCartItemEntity(): CartItemEntity {
    return CartItemEntity(
        id = id,
        description = description,
        price = price,
        title = title,
        categoryId = categoryId,
        image = image,
        name = name
    )

}

