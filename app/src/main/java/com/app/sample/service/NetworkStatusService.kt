package com.app.sample.service

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities


class NetworkStatusService(context: Context) {

    private var connectivityManager = context
        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    companion object {

        private var instance: NetworkStatusService? = null

        fun getInstance(context: Context): NetworkStatusService {
            if (instance == null) {
                instance = NetworkStatusService(context)
            }
            return instance!!
        }
    }


    fun isOffline(): Boolean {
        val nw = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            //for other device how are able to connect with Ethernet
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false

        }
    }
}