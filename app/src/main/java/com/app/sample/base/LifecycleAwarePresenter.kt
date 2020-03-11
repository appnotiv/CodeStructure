package com.app.sample.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

abstract class LifecycleAwarePresenter<V : LifecycleOwner>(var view: V) : BasePresenter {
    private var isResumed: Boolean = false

    val isNotSafeToCallViewBack: Boolean
        get() = !isResumed

    init {
        this.view.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        isResumed = true
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        isResumed = false
    }
}