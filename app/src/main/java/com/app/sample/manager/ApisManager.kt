package com.app.sample.manager

class ApisManager() {

    interface ApiCommonCallback {

        fun isSuccess()

        fun onError()
    }

}