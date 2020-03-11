package com.app.sample.presentation.splash

import android.os.Bundle
import com.app.sample.R
import com.app.sample.base.BaseActivity
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity(), SplashContract.SplashView {

    lateinit var job: Job

    lateinit var presenter: SplashContract.SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        instantiateDependencies()
    }

    private fun instantiateDependencies() {
        presenter = SplashPresenter(this)
    }

    private fun init() {
        job = launch {
            delay(2000)
            //launch activity code
        }
    }

    override fun onPause() {
        super.onPause()
        job.cancel()
    }

    override fun onResume() {
        super.onResume()
        init()
    }

    override fun onBackPressed() {
        doubleTapToExit()
    }
}
