package com.sevenpeakssoftware.patipan.base

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseAppCompatActivity : AppCompatActivity() {

  private var onNetworkCallback: OnNetWorkCallBack? = null
  private val connectivityManager: ConnectivityManager by lazy {
    getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
  }

  private val networkCallback: ConnectivityManager.NetworkCallback = object :
    ConnectivityManager.NetworkCallback() {

    override fun onAvailable(network: Network?) {
      onNetworkCallback?.onAvailable(network)
      super.onAvailable(network)
    }

    override fun onLost(network: Network?) {
      onNetworkCallback?.onLost(network)
      super.onLost(network)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setUpConnectivityNetwork()
  }

  private fun setUpConnectivityNetwork() {
    val request =
      NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addCapability(NetworkCapabilities.NET_CAPABILITY_NOT_RESTRICTED).build()

    connectivityManager.registerNetworkCallback(request, networkCallback)
  }

  fun setNetWorkCallBackListener(onNetWorkCallBack: OnNetWorkCallBack){
    this.onNetworkCallback = onNetWorkCallBack
  }


  interface OnNetWorkCallBack {
    fun onAvailable(network: Network?)
    fun onLost(network: Network?)
  }
}