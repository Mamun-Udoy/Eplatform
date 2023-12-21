package com.example.eplatform.utils

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