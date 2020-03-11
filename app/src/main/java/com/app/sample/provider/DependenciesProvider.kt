package com.app.sample.provider

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.app.sample.manager.ApisManager
import com.app.sample.pref.SharedPref
import com.app.sample.rest.ApiClient
import com.app.sample.service.NetworkStatusService

class DependenciesProvider(private val context: Context) {

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: DependenciesProvider? = null

        fun getInstance(context: Context): DependenciesProvider {
            check(context is Application) { "Context for a provider should be the application context" }

            if (instance == null) {
                instance = DependenciesProvider(context)
            }

            return instance!!
        }
    }

    val getNetworkStatusService = NetworkStatusService.getInstance(context)

    val apisManager = ApisManager()
}