package com.example.eplatform.utils

import android.content.Context
import android.net.ConnectivityManager
import androidx.navigation.NavController
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

fun ProductResponse.ProductResItem.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        description = description,
        price = price,
        title = title,
        categoryId = category?.id,
        image = images.toString(),
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
        image = images.toString(),
        name = category?.name
    )
}

