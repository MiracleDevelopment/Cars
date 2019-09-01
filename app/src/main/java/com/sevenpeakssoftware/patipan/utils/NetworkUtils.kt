package com.sevenpeakssoftware.patipan.utils

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtils {

  fun checkNetworkUtils(context: Context?) : Boolean {
    context?:return false

    val connectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
  }
}