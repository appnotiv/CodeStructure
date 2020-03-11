package com.app.sample.base

import androidx.lifecycle.LifecycleOwner
import com.app.sample.manager.error.NetworkingError

interface BaseView<P : BasePresenter> : LifecycleOwner {

    fun showProgress()

    fun hideProgress()

    fun showMessage(message: Any)

    fun showError(error: NetworkingError)

}