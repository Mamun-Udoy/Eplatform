package com.example.eplatform.utils

import android.content.Context
import android.net.ConnectivityManager
import androidx.navigation.NavController

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