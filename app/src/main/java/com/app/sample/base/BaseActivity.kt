package com.app.sample.base

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.app.sample.R
import com.app.sample.customs.CustomProgressDialog
import com.app.sample.manager.error.NetworkingError
import com.app.sample.utility.snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext


abstract class BaseActivity : AppCompatActivity(), CoroutineScope {

    companion object {
        var dialogShowing = false
    }

    private lateinit var job: Job
    private var progress: CustomProgressDialog? = null

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = Color.TRANSPARENT
        job = Job()
        progress = CustomProgressDialog(this)
    }

    fun showProgress() {
        if (!this.isFinishing) {
            progress?.show()
        }
    }

    fun hideProgress() {
        if (!this.isFinishing && progress?.isShowing == true) {
            progress?.dismiss()
        }
    }

    fun showMessage(message: Any) {
        val stMsg = when (message) {
            is String -> message.toString()
            is Int -> resources.getString(message)
            else -> ""
        }

        findViewById<View>(android.R.id.content).apply {
            snackbar(stMsg)
        }
    }

    fun showError(error: NetworkingError) {
        val message = error.message
        if (message.isBlank()) {
            val messageResId: Int = when (error.errorType) {
                NetworkingError.ErrorType.NO_INTERNET -> R.string.no_internet_connection
                NetworkingError.ErrorType.FAILED_REQUEST -> R.string.something_went_wrong
                NetworkingError.ErrorType.UNEXPECTED_RESPONSE_CODE -> R.string.something_went_wrong
            }
            findViewById<View>(android.R.id.content).apply {
                snackbar(messageResId)
            }
        } else {
            findViewById<View>(android.R.id.content).apply {
                snackbar(message)
            }
        }
    }

    protected fun addFragment(
        @IdRes containerViewId: Int, fragment: Fragment,
        fragmentTag: String
    ) {
        supportFragmentManager
            .beginTransaction()
            .add(containerViewId, fragment, fragmentTag)
            .disallowAddToBackStack()
            .commit()
    }

    protected fun replaceFragment(
        @IdRes containerViewId: Int, fragment: Fragment,
        fragmentTag: String,
        addToBackStack: Boolean? = false
    ) {
        supportFragmentManager
            .beginTransaction()
            .replace(containerViewId, fragment, fragmentTag)
            .addToBackStack(if (addToBackStack!!) fragment::class.java.simpleName else null)
            .commit()
    }

    protected fun replaceFragmentWithPop(
        @IdRes containerViewId: Int, fragment: Fragment,
        fragmentTag: String,
        addToBackStack: Boolean? = false
    ) {
        supportFragmentManager
            .beginTransaction()
            .replace(containerViewId, fragment, fragmentTag)
            .disallowAddToBackStack()
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    /**
     * Double tap to Exit application
     */
    private var backCheck = false
    fun doubleTapToExit() {
        if (backCheck) {
            finishAffinity()
            return
        }

        backCheck = true
        showMessage("Please click BACK again to exit")

        Handler().postDelayed({ backCheck = false; }, 2000)
    }
}