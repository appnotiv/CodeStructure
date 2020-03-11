package com.app.sample.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.app.sample.R
import com.app.sample.customs.CustomProgressDialog
import com.app.sample.manager.error.NetworkingError
import com.app.sample.utility.snackbar

open class BaseFragment : Fragment() {

    private var progress: CustomProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progress = CustomProgressDialog(context!!)
    }

    fun showProgress() {
        if (!activity!!.isFinishing) {
            progress?.show()
        }
    }

    fun hideProgress() {
        if (!activity!!.isFinishing && progress?.isShowing == true) {
            progress?.dismiss()
        }
    }

    fun showMessage(message: Any) {
        val stMsg = when (message) {
            is String -> message.toString()
            is Int -> resources.getString(message)
            else -> ""
        }
        activity!!.findViewById<View>(android.R.id.content).apply {
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
            activity!!.findViewById<View>(android.R.id.content).apply {
                snackbar(messageResId)
            }
        } else {
            activity!!.findViewById<View>(android.R.id.content).apply {
                snackbar(message)
            }
        }
    }

}