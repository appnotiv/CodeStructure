package com.app.sample

import android.app.Application

lateinit var instance: MyApplication

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}