package com.app.sample.presentation.splash

import com.app.sample.base.LifecycleAwarePresenter

class SplashPresenter(view: SplashContract.SplashView) :
    LifecycleAwarePresenter<SplashContract.SplashView>(
        view
    ),
    SplashContract.SplashPresenter {
}